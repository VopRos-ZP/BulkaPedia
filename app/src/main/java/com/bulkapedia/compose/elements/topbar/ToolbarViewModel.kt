package com.bulkapedia.compose.elements.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
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

    private val _navController: MutableStateFlow<NavController?> = MutableStateFlow(null)
    val navController: StateFlow<NavController?> = _navController

    fun update(title: String, showBack: Boolean, navController: NavController) {
        setTitle(title)
        setShowBack(showBack)
        setNavController(navController)
    }

    private fun setTitle(title: String) {
        viewModelScope.launch {
            _title.emit(title)
        }
    }

    private fun setShowBack(show: Boolean) {
        viewModelScope.launch {
            _showBack.emit(show)
        }
    }

    private fun setNavController(navController: NavController) {
        viewModelScope.launch {
            _navController.emit(navController)
        }
    }

}