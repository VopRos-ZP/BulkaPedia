package com.bulkapedia.compose.screens.createset.selectgear

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.util.HeroType
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearSet
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.domain.gears.GearRepository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectGearViewModel @Inject constructor(
    private val gearsRepository: GearRepository
) : ViewModel() {

    val gears: SnapshotStateList<Gear> = mutableStateListOf()

    private var listener: ListenerRegistration? = null

    fun fetchGears(cell: GearCell, hero: Hero) {
        listener = gearsRepository.fetchAll(CallBack({ allGears ->
            val allGS = allGears.filter { it.gearCell == cell.name.lowercase() }
            val defaultGears = allGS.filter { it.gearSet == GearSet.NONE.name }
            val setsGears = allGS
                .filter { it.gearSet == fetchGearTypeByHeroType(hero.type) }
            val personal = allGS
                .filter { it.gearSet == GearSet.PERSONAL.name }
                .filter { hero.personalGears[cell.name.lowercase()]!! == it.gearId }
            val heroGears = (defaultGears + setsGears + personal)
                .toMutableList().apply {
                    val index = indexOfFirst { it.gearId.contains("empty") }
                    val g = this[index]
                    removeAt(index)
                    add(0, g)
                }
            gears.clear()
            gears.addAll(heroGears)
        }) {})
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