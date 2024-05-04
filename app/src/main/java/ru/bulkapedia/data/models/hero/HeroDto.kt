package ru.bulkapedia.data.models.hero

import com.google.firebase.firestore.DocumentId
import ru.bulkapedia.domain.model.HeroType

data class HeroDto(
    @DocumentId
    val id: String = "",
    val type: String = HeroType.SHORTGUN.name,
    val active: Boolean = false,
    val image: String = "",
)
