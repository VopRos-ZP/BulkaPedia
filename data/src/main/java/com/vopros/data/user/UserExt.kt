package com.vopros.data.user

import com.google.firebase.database.DataSnapshot
import com.vopros.domain.user.Stats
import com.vopros.domain.user.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun DataSnapshot.toUser(): User? {
    return try {
        val strings = mutableMapOf<String, String>()
        val mains = mutableMapOf<String, Stats>()
        children.forEach { data ->
            if (data.key!! == "mains"){
                data.children.forEach { main ->
                    val statsMap = mutableMapOf<String, Double>()
                    main.children.forEach { mainData ->
                        statsMap += mainData.key!! to mainData.value!!.toString().toDouble()
                    }
                    if (!main.key.isNullOrEmpty() && statsMap.containsKey("kills")) {
                        mains += main.key!! to Stats(
                            statsMap["kills"]!!.toInt(),
                            statsMap["winrate"]!!.toInt(),
                            statsMap["revives"]!!.toInt()
                        )
                    }
                }
            } else
                strings += data.key!! to (data.value as String)
        }
        val now = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        User(key!!, strings["email"]!!, strings["password"]!!, strings["nickname"]!!,
            updateEmail = strings["updateEmail"] ?: now,
            updateNickname = strings["updateENickname"] ?: now,
            mains)
    } catch (_: Exception) { null }
}

fun User.toData(): Map<String, Any?> {
    return mapOf(
        "email" to email,
        "password" to password,
        "nickname" to nickname,
        "updateEmail" to updateEmail,
        "updateNickname" to updateNickname,
        "mains" to mains,
    )
}