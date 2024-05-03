package ru.bulkapedia.data.models.map

import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.repository.MapsRepository
import ru.bulkapedia.domain.utils.Table
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor() : MapsRepository {

    private val collection = Firebase.firestore.collection(Table.MAPS)

    private val transform: suspend (QuerySnapshot) -> List<GameMap> = {
        it.toObjects(GameMapDto::class.java).map(GameMapDto::toGameMap)
    }

    override val maps: Flow<List<GameMap>> = collection
        .snapshots()
        .map(transform)

    override val map: (String) -> Flow<GameMap> = { id ->
        callbackFlow {
            val listener = collection.document(id).addSnapshotListener { value, error ->
                if (value != null) {
                    trySend(value.toObject<GameMapDto>()!!.toGameMap())
                }
                if (error != null) {
                    cancel(error.stackTraceToString(), error)
                }
            }
            awaitClose { listener.remove() }
        }
    }

}