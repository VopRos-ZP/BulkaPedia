package com.vopros.domain.set

import kotlinx.serialization.Serializable

@Serializable
enum class GearCell {
    HEAD,
    BODY,
    ARM,
    LEG,
    DECOR,
    DEVICE;
}