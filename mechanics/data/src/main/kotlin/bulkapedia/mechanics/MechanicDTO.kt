package bulkapedia.mechanics

import com.google.firebase.firestore.DocumentId

data class MechanicDTO(
    @DocumentId var mechanicId: String = "",
    var icon: String = "",
    var title: String = "",
    var description: String = "",
    var video: String = "",
)
