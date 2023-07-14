package bulkapedia.instances

import bulkapedia.effects.PersonalEffect
import bulkapedia.effects.PersonalEffectDTO
import bulkapedia.effects.utils.toDTO
import bulkapedia.effects.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class EffectsRepositoryImpl(ref: CollectionReference) : RepositoryImpl<PersonalEffect>(
    ref, { it.toObject(PersonalEffectDTO::class.java)?.toPOJO() },
    { it.personalId }, { it.toDTO() }
)