package ru.bulkapedia.domain.model

data class GearPrice(
    val id: Int,
    val gearsCommon: Int,
    val priceCommon: Int,
    val gearsRare: Int,
    val priceRare: Int,
    val gearsPersonal: Int,
    val pricePersonal: Int,
)
