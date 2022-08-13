package com.bulkapedia.maps

import com.bulkapedia.R

class MapList {

    companion object {

        val maps = listOf(
            // Battle Royale Mode
            Map(R.id.hotelMapItem, R.drawable.hotel, R.drawable.hotel_spawns, R.string.hotel, R.string.battle_royale_mode),
            Map(R.id.railwayStationMapItem, R.drawable.railway_station, R.drawable.railway_station_spawns, R.string.railway_station, R.string.battle_royale_mode),
            Map(R.id.casinoMapItem, R.drawable.casino, R.drawable.casino_spawns, R.string.casino, R.string.battle_royale_mode),
            Map(R.id.villaMapItem, R.drawable.villa, R.drawable.villa_spawns, R.string.villa, R.string.battle_royale_mode),
            Map(R.id.roadMotelMapItem, R.drawable.road_motel, R.drawable.road_motel_spawns, R.string.road_motel, R.string.battle_royale_mode),
            Map(R.id.villageItem, R.drawable.village, R.drawable.village_spawns, R.string.village, R.string.battle_royale_mode),
            Map(R.id.cityItem, R.drawable.city, R.drawable.city_spawns, R.string.city, R.string.battle_royale_mode),
            Map(R.id.cityGardenItem, R.drawable.city_garden, R.drawable.city_garden_spawns, R.string.city_garden, R.string.battle_royale_mode),
            Map(R.id.policeStationItem, R.drawable.police_station, R.drawable.police_station_spawns, R.string.police_station, R.string.battle_royale_mode),
            Map(R.id.sawmillItem, R.drawable.sawmill, R.drawable.sawmill_spawns, R.string.sawmill, R.string.battle_royale_mode),
            // Kings Of Hill
            Map(R.id.hospitalItem, R.drawable.hospital, R.drawable.hospital_spawns, R.string.hospital, R.string.kings_of_hill_mode),
            Map(R.id.circusItem, R.drawable.circus, R.drawable.circus_spawns, R.string.circus, R.string.kings_of_hill_mode),
            Map(R.id.psychiatricHospitalItem, R.drawable.psychiatric_hospital, R.drawable.psychiatric_hospital_spawns, R.string.psychiatric_hospital, R.string.kings_of_hill_mode),
            Map(R.id.forgottenBazarItem, R.drawable.forgotten_bazar, R.drawable.forgotten_bazar_spawns, R.string.forgotten_bazar, R.string.kings_of_hill_mode),
            // Squad
            Map(R.id.sewerageItem, R.drawable.sewerage, R.drawable.sewerage_spawns, R.string.sewerage, R.string.squad_mode),
            Map(R.id.dungeonItem, R.drawable.dungeon, R.drawable.dungeon_spawns, R.string.dungeon, R.string.squad_mode),
            Map(R.id.amusementParkItem, R.drawable.amusement_park, R.drawable.amusement_park_spawns, R.string.amusement_park, R.string.squad_mode),
            Map(R.id.factoryItem, R.drawable.factory, R.drawable.factory_spawns, R.string.factory, R.string.squad_mode),
            Map(R.id.mysteriousIslandItem, R.drawable.mysterious_island, R.drawable.mysterious_island_spawns, R.string.mysterious_island, R.string.squad_mode),
            // Sabotage
            Map(R.id.undergroundBaseItem, R.drawable.underground_base, R.drawable.underground_base, R.string.underground_base, R.string.sabotage_mode),
            Map(R.id.militaryStorageItem, R.drawable.military_storage, R.drawable.military_storage_spawns, R.string.military_storage, R.string.sabotage_mode),
            Map(R.id.secretFloorItem, R.drawable.secret_floor, R.drawable.secret_floor_spawns, R.string.secret_floor, R.string.sabotage_mode),
            Map(R.id.boardingItem, R.drawable.boarding, R.drawable.boarding_spawns, R.string.boarding, R.string.sabotage_mode),
        )

    }

}