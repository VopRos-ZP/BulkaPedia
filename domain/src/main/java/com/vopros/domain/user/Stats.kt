package com.vopros.domain.user

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    var kills: Int = 0,
    var winrate: Int = 0,
    var revives: Int = 0
)
