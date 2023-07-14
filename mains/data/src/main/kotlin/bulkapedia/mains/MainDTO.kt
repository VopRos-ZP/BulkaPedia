package bulkapedia.mains

import com.google.firebase.firestore.DocumentId

data class MainDTO(
    @DocumentId var mainId: String = "",
    var kills: Int = 10000,
    var winrate: Double = 50.0,
    var revives: Int = 500
)
