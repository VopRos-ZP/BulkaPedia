package com.bulkapedia.compose.data.labels

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    var kills: Int,
    var winrate: Int,
    var revives: Int
)
