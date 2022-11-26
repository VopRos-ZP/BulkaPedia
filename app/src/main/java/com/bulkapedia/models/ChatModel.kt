package com.bulkapedia.models

data class ChatModel (
    var author: String,
    var receiver: String,
    var date: String,
    var text: String,
    var image: String
): java.io.Serializable
