package com.bulkapedia.compose.elements.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToolbarViewModel @Inject constructor(): ViewModel() {

    private val _title: MutableStateFlow<String> = MutableStateFlow("")
    val titleFlow: StateFlow<String> = _title

    private val _showBack: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showBackFlow: StateFlow<Boolean> = _showBack

    private val _onBackClick: MutableStateFlow<() -> Unit> = MutableStateFlow {}
    val onBackClickFlow: StateFlow<() -> Unit> = _onBackClick

    fun update(title: String, showBack: Boolean) {
        setTitle(title)
        setShowBack(showBack)
    }

    fun setTitle(title: String) {
        viewModelScope.launch {
            _title.emit(title)
        }
    }

    fun setShowBack(show: Boolean) {
        viewModelScope.launch {
            _showBack.emit(show)
        }
    }

    fun setOnBackClick(onBackClick: () -> Unit) {
        viewModelScope.launch {
            _onBackClick.emit(onBackClick)
        }
    }

}