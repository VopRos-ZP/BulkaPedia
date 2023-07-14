package bulkapedia.gears

import bulkapedia.effects.Effect
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class GearDTO(
    @DocumentId var id: String = "",
    @PropertyName("gearCell") var gearCell: String = "",
    @PropertyName("gearSet") var gearSet: String = "",
    @PropertyName("icon") var icon: String = "",
    @PropertyName("ranks") var ranks: Map<String, List<Int>> = emptyMap(),
    @PropertyName("effects") var effects: List<Effect> = emptyList()
)