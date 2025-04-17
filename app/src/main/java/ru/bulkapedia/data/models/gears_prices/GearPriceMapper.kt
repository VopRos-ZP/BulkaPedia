package ru.bulkapedia.data.models.gears_prices

import ru.bulkapedia.domain.model.GearPrice

fun GearPriceDto.fromDto(): GearPrice = GearPrice(
    id = id,
    gearsCommon = gearsCommon,
    priceCommon = priceCommon,
    gearsRare = gearsRare,
    priceRare = priceRare,
    gearsPersonal = gearsPersonal,
    pricePersonal = pricePersonal,
)

fun GearPrice.toDto(): GearPriceDto = GearPriceDto(
    id = id,
    gearsCommon = gearsCommon,
    priceCommon = priceCommon,
    gearsRare = gearsRare,
    priceRare = priceRare,
    gearsPersonal = gearsPersonal,
    pricePersonal = pricePersonal,
)