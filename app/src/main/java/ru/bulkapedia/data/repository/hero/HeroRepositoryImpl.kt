package ru.bulkapedia.data.repository.hero

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.repository.HeroRepository

class HeroRepositoryImpl(wrapper: SupabaseWrapper) : HeroRepository {

    @OptIn(SupabaseExperimental::class)
    override val heroes = wrapper.postgres
        .from("heroes")
        .selectAsFlow(Hero::id)
        .distinctUntilChanged()
        .map { it.map(Hero::toPojo) }

}