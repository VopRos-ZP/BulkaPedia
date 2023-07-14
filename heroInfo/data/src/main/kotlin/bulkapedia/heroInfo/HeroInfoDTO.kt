package bulkapedia.heroInfo

import com.google.firebase.firestore.DocumentId

data class HeroInfoDTO(
    @DocumentId var heroInfoId: String = "",
    var video: String = "",
    var hero: String = "",
    var description: String = ""
)
