package bulkapedia.instances

import bulkapedia.gears.Gear
import bulkapedia.gears.GearDTO
import bulkapedia.gears.utils.toDTO
import bulkapedia.gears.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class GearsRepositoryImpl(ref: CollectionReference) : RepositoryImpl<Gear>(
    ref, { it.toObject(GearDTO::class.java)?.toPOJO() },
    { it.gearId }, { it.toDTO() }
)