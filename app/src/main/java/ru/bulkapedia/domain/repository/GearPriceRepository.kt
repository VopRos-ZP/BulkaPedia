package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.GearPrice

interface GearPriceRepository {

    val gearPrices: Flow<List<GearPrice>>

}