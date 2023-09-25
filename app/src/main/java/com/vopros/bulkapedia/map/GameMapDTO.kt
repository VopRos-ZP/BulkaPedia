package com.vopros.bulkapedia.map

import com.google.firebase.firestore.DocumentId

data class GameMapDTO(
    @DocumentId var id: String = "",
    var image: String = "",
    var spawns: String = "",
    var mode: String = ""
)
