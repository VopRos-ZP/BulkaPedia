package com.bulkapedia.compose.data

import android.os.Parcelable
import com.bulkapedia.compose.data.labels.Stats
import com.google.firebase.database.DataSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var email: String,
    var password: String,
    var nickname: String,
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
                User(strings["email"]!!, strings["password"]!!,
                    strings["nickname"]!!, mains)
            } catch (_: Exception) { null }
        }

    }

}
