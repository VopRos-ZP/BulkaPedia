package com.vopros.bulkapedia.ui.components.topbar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor() : ViewModel() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _showBack = MutableStateFlow(false)
    val showBack = _showBack.asStateFlow()

    private val _navController = MutableStateFlow<NavController?>(null)
    val navController = _navController.asStateFlow()

    fun update(title: String, showBack: Boolean, controller: NavController) {
        viewModelScope.launch {
            _title.emit(title)
            _showBack.emit(showBack)
            _navController.emit(controller)
        }
    }

}