package com.bulkapedia.data.sets

data class UserSetDTO(
    var author: String = "",
    var hero: String = "",
    var head: String = "",
    var body: String = "",
    var arm: String = "",
    var leg: String = "",
    var decor: String = "",
    var device: String = "",
    var user_like_ids: List<String> = emptyList()
)