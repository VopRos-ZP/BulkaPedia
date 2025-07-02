package ru.bulkapedia.data.models.gears_prices

import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.repository.GearPriceRepository
import ru.bulkapedia.domain.utils.Table

@OptIn(SupabaseExperimental::class)
class GearPriceRepositoryImpl(
    supabaseHelper: SupabaseHelper
) : GearPriceRepository {

    override val gearPrices = supabaseHelper.postgrest
        .from(Table.GEAR_PRICES)
        .selectAsFlow(GearPriceDto::id)
        .map { it.map(GearPriceDto::fromDto) }

}