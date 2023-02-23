package com.bulkapedia.compose.screens.createset.selectgear

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.util.HeroType
import com.bulkapedia.compose.data.gears.Gear
import com.bulkapedia.compose.data.gears.GearSet
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.sets.GearCell
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class SelectGearEvent {
    data class LoadingData(val cell: GearCell, val hero: Hero): SelectGearEvent()
}

sealed class SelectGearViewState {
    object Loading: SelectGearViewState()
    data class Enter(val gears: List<Gear>): SelectGearViewState()
    data class Error(val message: String): SelectGearViewState()
}

@HiltViewModel
class SelectGearViewModel @Inject constructor() : ViewModel(), EventHandler<SelectGearEvent> {

    private val _liveData: MutableLiveData<SelectGearViewState> = MutableLiveData(SelectGearViewState.Loading)
    val liveData: LiveData<SelectGearViewState> = _liveData

    override fun obtainEvent(event: SelectGearEvent) {
        when (val state = _liveData.value!!) {
            is SelectGearViewState.Loading -> reduce(event, state)
            is SelectGearViewState.Enter -> reduce(event, state)
            is SelectGearViewState.Error -> reduce(event, state)
        }
    }

    private fun reduce(event: SelectGearEvent, state: SelectGearViewState.Loading) {
        when (event) {
            is SelectGearEvent.LoadingData -> fetchGears(event.cell, event.hero)
        }
    }

    private fun reduce(event: SelectGearEvent, state: SelectGearViewState.Enter) {
        when (event) {
            is SelectGearEvent.LoadingData -> fetchGears(event.cell, event.hero)
        }
    }

    private fun reduce(event: SelectGearEvent, state: SelectGearViewState.Error) {
        when (event) {
            is SelectGearEvent.LoadingData -> fetchGears(event.cell, event.hero)
        }
    }

    private fun fetchGears(cell: GearCell, hero: Hero) {
        Database().addGearsSnapshotListener { allGears ->
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
            _liveData.postValue(SelectGearViewState.Enter(gears))
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

}