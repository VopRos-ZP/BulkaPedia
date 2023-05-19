package com.vopros.data.message

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.message.Message

class MessageRepositoryImpl : RepositoryImpl<Message>(
    Database.messages, { it.toMessage() }, "Сообщение не найдено!"
)