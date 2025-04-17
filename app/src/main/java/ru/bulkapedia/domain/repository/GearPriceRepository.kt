package ru.bulkapedia.domain.repository

import ru.bulkapedia.domain.model.GearPrice

interface GearPriceRepository {

    suspend fun fetchAll(): List<GearPrice>

}