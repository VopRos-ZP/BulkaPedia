package ru.bulkapedia.data.repository.fraction

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.SupabaseWrapper
import ru.bulkapedia.domain.model.Fraction
import ru.bulkapedia.domain.repository.FractionRepository

class FractionRepositoryImpl(wrapper: SupabaseWrapper) : FractionRepository {

    @OptIn(SupabaseExperimental::class)
    override val fractions: Flow<List<Fraction>> = wrapper.postgres.from("fraction_images")
        .selectAsFlow(ru.bulkapedia.data.repository.hero.Fraction::id)
        .map { it.map(ru.bulkapedia.data.repository.hero.Fraction::toPojo) }
        .distinctUntilChanged()

}