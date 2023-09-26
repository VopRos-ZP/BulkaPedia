package com.vopros.bulkapedia.userSet

import com.google.firebase.firestore.DocumentId

data class UserSetDTO(
    @DocumentId var documentId: String = "",
    var author: String = "",
    var gears: Map<String, String> = emptyMap(),
    var hero: String = "arnie",
    var liked: List<String> = emptyList()
)
