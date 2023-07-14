package com.bulkapedia.compose.screens.createset.selectgear

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import bulkapedia.Callback
import bulkapedia.StoreRepository
import com.bulkapedia.compose.util.HeroType
import bulkapedia.gears.Gear
import bulkapedia.gears.GearSet
import bulkapedia.heroes.Hero
import bulkapedia.gears.GearCell
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectGearViewModel @Inject constructor(
    private val gearsRepository: StoreRepository<Gear>
) : ViewModel() {

    val gears: SnapshotStateList<Gear> = mutableStateListOf()

    private var listener: ListenerRegistration? = null

    fun fetchGears(cell: GearCell, hero: Hero) {
        listener = gearsRepository.listenAll(Callback({ allGears ->
            val allGS = allGears.filter { it.cell == cell }
            val defaultGears = allGS.filter { it.set == GearSet.NONE }
            val setsGears = allGS
                .filter { it.set == fetchGearTypeByHeroType(hero.type) }
            val personal = allGS
                .filter { it.set == GearSet.PERSONAL }
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
        }))
    }

    private fun fetchGearTypeByHeroType(type: String): GearSet {
        return when (type) {
            HeroType.SHORTGUNS.str() -> GearSet.WHITE_INDEX
            HeroType.SCOUTS.str() -> GearSet.PARTS
            HeroType.SNIPERS.str() -> GearSet.DARK_IMPLANT
            HeroType.TANKS.str() -> GearSet.HEAVY_PORT
            HeroType.TROOPERS.str() -> GearSet.BIO_NODE
            else -> GearSet.NONE
        }
    }

    fun dispose() {
        listener?.remove()
    }

}