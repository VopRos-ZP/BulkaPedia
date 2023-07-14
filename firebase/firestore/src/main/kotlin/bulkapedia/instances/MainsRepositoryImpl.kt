package bulkapedia.instances

import bulkapedia.mains.Main
import bulkapedia.mains.MainDTO
import bulkapedia.mains.utils.toDTO
import bulkapedia.mains.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class MainsRepositoryImpl(ref: CollectionReference): RepositoryImpl<Main>(
    ref, { it.toObject(MainDTO::class.java)?.toPOJO() },
    { it.mainId }, { it.toDTO() }
)