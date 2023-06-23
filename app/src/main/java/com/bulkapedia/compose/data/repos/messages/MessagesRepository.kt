package com.bulkapedia.compose.data.repos.messages

import com.bulkapedia.compose.data.repos.messages.Message.Companion.toMessage
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class MessagesRepositoryImpl : FirestoreRepository<Message>(FirestoreDB.messages, { it.toMessage() })

typealias MessagesRepository = Repository<Message>