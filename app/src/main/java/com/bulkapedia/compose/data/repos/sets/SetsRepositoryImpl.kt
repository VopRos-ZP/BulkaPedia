package com.bulkapedia.compose.data.repos.sets

import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.bulkapedia.compose.data.repos.sets.UserSet.Companion.toUserSet

class SetsRepositoryImpl : FirestoreRepository<UserSet>(FirestoreDB.sets, { it.toUserSet() })

typealias SetsRepository = Repository<UserSet>