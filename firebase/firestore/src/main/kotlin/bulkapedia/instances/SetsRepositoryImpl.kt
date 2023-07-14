package bulkapedia.instances

import bulkapedia.sets.UserSet
import bulkapedia.sets.UserSetDTO
import bulkapedia.sets.utils.toDTO
import bulkapedia.sets.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class SetsRepositoryImpl(ref: CollectionReference): RepositoryImpl<UserSet>(
    ref, { it.toObject(UserSetDTO::class.java)?.toPOJO() },
    { it.setId }, { it.toDTO() }
)