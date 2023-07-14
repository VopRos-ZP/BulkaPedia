package bulkapedia.instances

import bulkapedia.mechanics.Mechanic
import bulkapedia.mechanics.MechanicDTO
import bulkapedia.mechanics.utils.toDTO
import bulkapedia.mechanics.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class MechanicsRepositoryImpl(ref: CollectionReference): RepositoryImpl<Mechanic>(
    ref, { it.toObject(MechanicDTO::class.java)?.toPOJO() },
    { it.mechanicId }, { it.toDTO() }
)