package com.bulkapedia.compose.data.repos.stats

import com.bulkapedia.compose.data.repos.stats.Stats.Companion.toStats
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class StatsRepositoryImpl : FirestoreRepository<Stats>(FirestoreDB.mains, { it.toStats() })

typealias StatsRepository = Repository<Stats>