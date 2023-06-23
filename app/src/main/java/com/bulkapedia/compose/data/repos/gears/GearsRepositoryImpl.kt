package com.bulkapedia.compose.data.repos.gears

import com.bulkapedia.compose.data.repos.gears.Gear.Companion.toGear
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class GearsRepositoryImpl : FirestoreRepository<Gear>(FirestoreDB.gears, { it.toGear() })

typealias GearsRepository = Repository<Gear>