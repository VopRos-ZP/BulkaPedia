package com.bulkapedia.domain.messages

import com.bulkapedia.data.Repository
import com.bulkapedia.domain.core.RepositoryImpl
import com.bulkapedia.data.messages.Message
import com.bulkapedia.data.messages.Message.Companion.toData
import com.bulkapedia.data.messages.Message.Companion.toMessage
import com.bulkapedia.domain.core.Refs

class MessageRepositoryImpl: RepositoryImpl<Message>(
    Refs.Store.messages, { it.toMessage() }, { it.messageId }, { it.toData() }
)

typealias MessageRepository = Repository<Message>