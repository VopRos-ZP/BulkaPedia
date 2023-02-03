package com.bulkapedia.compose.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.elements.Tag
import com.bulkapedia.compose.util.HeroType
import com.bulkapedia.compose.util.MapType
import com.bulkapedia.compose.util.SortType
import kotlinx.coroutines.launch

data class TagViewState(
    val tagId: Int = 0,
    val selected: Boolean = false,
    val sortType: SortType<*> = SortType.None
)

class TagViewModel : ViewModel() {

    private val _liveData: MutableLiveData<TagViewState> = MutableLiveData(TagViewState())

    val viewState: LiveData<TagViewState> = _liveData

    fun toggle(tag: Tag, viewState: TagViewState?) {
        viewModelScope.launch {
            val sortType = getSortType(tag.text)
            val selected = if (viewState?.tagId == tag.id) viewState.selected.not() else true
            _liveData.postValue(viewState?.copy(
                tagId = tag.id,
                selected = selected,
                sortType = sortType
            ))
        }
    }

    private fun getSortType(text: String) : SortType<*> {
        val hPI = HeroType.values().map { it.strRu() }.indexOf(text)
        val mPI = MapType.values().map { it.strRu() }.indexOf(text)
        return if (hPI != -1) SortType.ByHero(HeroType.values()[hPI])
        else if (mPI != -1) SortType.ByMap(MapType.values()[mPI])
        else SortType.None
    }

}