package ru.bulkapedia.data.models.gears_prices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GearPriceDto(
    @SerialName("id")
    val id: Int,
    @SerialName("gears_common")
    val gearsCommon: Int,
    @SerialName("price_common")
    val priceCommon: Int,
    @SerialName("gears_rare")
    val gearsRare: Int,
    @SerialName("price_rare")
    val priceRare: Int,
    @SerialName("gears_personal")
    val gearsPersonal: Int,
    @SerialName("price_personal")
    val pricePersonal: Int,
)
