package com.bulkapedia.compose.screens.createset.selectgear

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.util.HeroType
import com.bulkapedia.compose.data.repos.gears.Gear
import com.bulkapedia.compose.data.repos.gears.GearSet
import com.bulkapedia.compose.data.repos.gears.GearsRepository
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectGearViewModel @Inject constructor(
    private val gearsRepository: GearsRepository
) : ViewModel() {

    val _gears: SnapshotStateList<Gear> = mutableStateListOf()

    private var listener: ListenerRegistration? = null

    fun fetchGears(cell: GearCell, hero: Hero) {
        listener = gearsRepository.fetchAll { allGears ->
            val allGS = allGears.filter { it.cell == cell.name.lowercase() }
            val defaultGears = allGS.filter { it.gearSet == GearSet.NONE.name }
            val setsGears = allGS
                .filter { it.gearSet == fetchGearTypeByHeroType(hero.type) }
            val personal = allGS
                .filter { it.gearSet == GearSet.PERSONAL.name }
                .filter { hero.personalGears[cell.name.lowercase()]!! == it.name }
            val gears = (defaultGears + setsGears + personal)
                .toMutableList().apply {
                    val index = indexOfFirst { it.name.contains("empty") }
                    val g = this[index]
                    removeAt(index)
                    add(0, g)
                }
            _gears.clear()
            _gears.addAll(gears)
        }
    }

    private fun fetchGearTypeByHeroType(type: String): String {
        return when (type) {
            HeroType.SHORTGUNS.str() -> GearSet.WHITE_INDEX.name
            HeroType.SCOUTS.str() -> GearSet.PARTS.name
            HeroType.SNIPERS.str() -> GearSet.DARK_IMPLANT.name
            HeroType.TANKS.str() -> GearSet.HEAVY_PORT.name
            HeroType.TROOPERS.str() -> GearSet.BIO_NODE.name
            else -> ""
        }
    }

    fun dispose() {
        listener?.remove()
    }

}