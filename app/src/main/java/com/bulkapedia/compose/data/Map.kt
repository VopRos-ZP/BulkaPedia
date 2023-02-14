package com.bulkapedia.compose.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize
import com.bulkapedia.R

@Parcelize
data class Map(
    val mapId: String,
    val image: String,
    val imageSpawns: String,
    val name: String,
    val mode: String,
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toMap(): Map? {
            return try {
                Map(id, getString("image")!!, getString("imageSpawns")!!,
                    getString("name")!!, getString("mode")!!)
            } catch (_: Exception) { null }
        }
    }

    override fun toString(): String = "map"

}

data class MMap (
    val mapItem: Int,
    val mapImage: Int,
    val mapImageSpawns: Int,
    val mapName: Int,
    val mapMode: Int
)

class MapList {
    companion object {
        val maps = listOf(
            // Battle Royale Mode
            MMap(0, R.drawable.hotel, R.drawable.hotel_spawns, R.string.hotel, R.string.battle_royale_mode),
            MMap(0, R.drawable.railway_station, R.drawable.railway_station_spawns, R.string.railway_station, R.string.battle_royale_mode),
            MMap(0, R.drawable.casino, R.drawable.casino_spawns, R.string.casino, R.string.battle_royale_mode),
            MMap(0, R.drawable.villa, R.drawable.villa_spawns, R.string.villa, R.string.battle_royale_mode),
            MMap(0, R.drawable.road_motel, R.drawable.road_motel_spawns, R.string.road_motel, R.string.battle_royale_mode),
            MMap(0, R.drawable.village, R.drawable.village_spawns, R.string.village, R.string.battle_royale_mode),
            MMap(0, R.drawable.city, R.drawable.city_spawns, R.string.city, R.string.battle_royale_mode),
            MMap(0, R.drawable.city_garden, R.drawable.city_garden_spawns, R.string.city_garden, R.string.battle_royale_mode),
            MMap(0, R.drawable.police_station, R.drawable.police_station_spawns, R.string.police_station, R.string.battle_royale_mode),
            MMap(0, R.drawable.sawmill, R.drawable.sawmill_spawns, R.string.sawmill, R.string.battle_royale_mode),
            MMap(0, R.drawable.fortress, R.drawable.fortress_spawns, R.string.fortress, R.string.battle_royale_mode),
            // Kings Of Hill
            MMap(0, R.drawable.hospital, R.drawable.hospital_spawns, R.string.hospital, R.string.kings_of_hill_mode),
            MMap(0, R.drawable.circus, R.drawable.circus_spawns, R.string.circus, R.string.kings_of_hill_mode),
            MMap(0, R.drawable.psychiatric_hospital, R.drawable.psychiatric_hospital_spawns, R.string.psychiatric_hospital, R.string.kings_of_hill_mode),
            MMap(0, R.drawable.forgotten_bazar, R.drawable.forgotten_bazar_spawns, R.string.forgotten_bazar, R.string.kings_of_hill_mode),
            // Squad
            MMap(0, R.drawable.sewerage, R.drawable.sewerage_spawns, R.string.sewerage, R.string.squad_mode),
            MMap(0, R.drawable.dungeon, R.drawable.dungeon_spawns, R.string.dungeon, R.string.squad_mode),
            MMap(0, R.drawable.amusement_park, R.drawable.amusement_park_spawns, R.string.amusement_park, R.string.squad_mode),
            MMap(0, R.drawable.factory, R.drawable.factory_spawns, R.string.factory, R.string.squad_mode),
            MMap(0, R.drawable.mysterious_island, R.drawable.mysterious_island_spawns, R.string.mysterious_island, R.string.squad_mode),
            // Sabotage
            MMap(0, R.drawable.underground_base, R.drawable.underground_base, R.string.underground_base, R.string.sabotage_mode),
            MMap(0, R.drawable.military_storage, R.drawable.military_storage_spawns, R.string.military_storage, R.string.sabotage_mode),
            MMap(0, R.drawable.secret_floor, R.drawable.secret_floor_spawns, R.string.secret_floor, R.string.sabotage_mode),
            MMap(0, R.drawable.boarding, R.drawable.boarding_spawns, R.string.boarding, R.string.sabotage_mode),
        )
    }
}
