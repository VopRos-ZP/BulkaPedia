package bulkapedia.effects

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class PersonalEffectDTO(
    @DocumentId var personalId: String = "",
    @PropertyName("personal_2") var personalTwo: List<Effect> = emptyList(),
    @PropertyName("personal_4") var personalFour: List<Effect> = emptyList(),
    @PropertyName("personal_6") var personalSix: List<Effect> = emptyList()
)
