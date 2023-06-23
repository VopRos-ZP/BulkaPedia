package com.bulkapedia.compose.data.repos.mechanics

import com.bulkapedia.compose.data.repos.mechanics.Mechanic.Companion.toMechanic
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class MechanicsRepositoryImpl : FirestoreRepository<Mechanic>(FirestoreDB.mechanics, { it.toMechanic() })

typealias MechanicsRepository = Repository<Mechanic>