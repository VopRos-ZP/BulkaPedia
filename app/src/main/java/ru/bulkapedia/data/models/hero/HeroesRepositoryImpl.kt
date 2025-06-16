package ru.bulkapedia.data.models.hero

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.realtime.selectAsFlow
import io.github.jan.supabase.realtime.selectSingleValueAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.repository.HeroesRepository
import ru.bulkapedia.domain.utils.Table

class HeroesRepositoryImpl(
    private val supabaseHelper: SupabaseHelper
) : HeroesRepository {

    @OptIn(SupabaseExperimental::class)
    override val heroes = supabaseHelper.postgrest
        .from(Table.HEROES)
        .selectAsFlow(
            primaryKey = HeroDto::id
        )
        .map { it.map(HeroDto::fromDto) }

    @OptIn(SupabaseExperimental::class)
    override fun listenHero(id: String): Flow<Hero> = supabaseHelper.postgrest
        .from(Table.HEROES)
        .selectSingleValueAsFlow(
            primaryKey = HeroDto::id
        ) {
            filter("id", FilterOperator.EQ, id)
        }
        .map(HeroDto::fromDto)

}