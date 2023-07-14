package bulkapedia.maps

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class GameMapDTO(
    @DocumentId var mapId: String = "",
    @PropertyName("image") var image: String = "",
    @PropertyName("imageSpawns") var spawns: String = "",
    @PropertyName("mode") var mode: String = "",
    @PropertyName("name") var name: String = ""
)
