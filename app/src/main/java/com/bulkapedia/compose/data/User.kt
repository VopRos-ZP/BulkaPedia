package com.bulkapedia.compose.data

import android.os.Parcelable
import com.bulkapedia.compose.data.labels.Stats
import com.google.firebase.database.DataSnapshot
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Parcelize
data class User(
    var email: String,
    var password: String,
    var nickname: String,
    var updateEmail: String,
    var updateNickname: String,
    var mains: MutableMap<String, Stats>? = null
) : Parcelable {

    companion object {

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
                User(strings["email"]!!, strings["password"]!!, strings["nickname"]!!,
                    updateEmail = strings["updateEmail"] ?: now,
                    updateNickname = strings["updateENickname"] ?: now,
                    mains)
            } catch (_: Exception) { null }
        }

    }

}
