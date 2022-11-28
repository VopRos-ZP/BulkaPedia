package com.bulkapedia.views.temps.models

data class ChatModel (
    var author: String,
    var receiver: String,
    var date: String,
    var text: String,
    var image: String,
    var read: Boolean
): java.io.Serializable
