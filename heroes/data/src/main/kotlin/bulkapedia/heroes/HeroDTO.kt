package bulkapedia.heroes

import com.google.firebase.firestore.DocumentId

data class HeroDTO(
    @DocumentId var heroId: String = "",
    var name: String = "",
    var type: String = "",
    var icon: String = "",
    var difficult: String = "",
    var stats: Map<String, Number> = emptyMap(),
    var counterpicks: List<String> = emptyList(),
    var personalGears: Map<String, String> = emptyMap()
)
