package com.vopros.data.heroinfo

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.heroinfo.HeroInfo

fun DocumentSnapshot.toHeroInfo(): HeroInfo? {
    return try {
        HeroInfo(
            id, getString("hero")!!,
            getString("video")!!,
            getString("description")!!
        )
    } catch (_: Exception) { null }
}