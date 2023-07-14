package bulkapedia.instances

import bulkapedia.maps.GameMap
import bulkapedia.maps.GameMapDTO
import bulkapedia.maps.utils.toDTO
import bulkapedia.maps.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class MapsRepositoryImpl(ref: CollectionReference): RepositoryImpl<GameMap>(
    ref, { it.toObject(GameMapDTO::class.java)?.toPOJO() },
    { it.mapId }, { it.toDTO() }
)