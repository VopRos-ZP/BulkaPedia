package ru.bulkapedia.data.repository

import ru.bulkapedia.data.models.gears_prices.GearPriceDto
import ru.bulkapedia.data.models.gears_prices.fromDto
import ru.bulkapedia.data.supabase.SupabaseHelper
import ru.bulkapedia.domain.model.GearPrice
import ru.bulkapedia.domain.repository.GearPriceRepository
import ru.bulkapedia.domain.utils.Table

class GearPriceRepositoryImpl(
    private val supabaseHelper: SupabaseHelper
): GearPriceRepository {

    override suspend fun fetchAll(): List<GearPrice> {
        return supabaseHelper.postgrest[Table.GEAR_PRICES]
            .select()
            .decodeList<GearPriceDto>()
            .map(GearPriceDto::fromDto)
    }

}