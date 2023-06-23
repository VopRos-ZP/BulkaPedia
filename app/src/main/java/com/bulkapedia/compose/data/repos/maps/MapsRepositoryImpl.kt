package com.bulkapedia.compose.data.repos.maps

import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.bulkapedia.compose.data.repos.maps.Map.Companion.toMap

class MapsRepositoryImpl : FirestoreRepository<Map>(FirestoreDB.maps, { it.toMap() })

typealias MapsRepository = Repository<Map>