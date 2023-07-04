package com.bulkapedia.data.messages

data class MessageDTO(
    var author: String = "",
    var date: String = "",
    var image: String = "",
    var receiver: String = "",
    var text: String = "",
    var read: Boolean = false
)