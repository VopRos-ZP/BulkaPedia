package com.bulkapedia.data.gears

import com.bulkapedia.data.gears.dto.EffectDTO

data class Effect(
    val number: Int,
    val percent: Boolean,
    val description: String,
) {

    companion object {

        fun Effect.toDTO(): EffectDTO = EffectDTO(description, number, percent)

    }

}
