package com.vopros.bulkapedia.ui.components.topbar

import androidx.navigation.NavController
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor() : IntentViewModel<TopBarIntent>() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _showBack = MutableStateFlow(false)
    val showBack = _showBack.asStateFlow()

    private val _navController = MutableStateFlow<NavController?>(null)
    val navController = _navController.asStateFlow()

    override var reducer: Reducer<TopBarIntent> = Reducer { intent, _ ->
        when (intent) {
            is TopBarIntent.Update -> update(intent.title, intent.showBack, intent.controller)
        }
    }

    private suspend fun update(title: String, showBack: Boolean, controller: NavController): ViewState {
        _title.emit(title)
        _showBack.emit(showBack)
        _navController.emit(controller)
        return ViewState.Success(Triple(this.title, this.showBack, this.navController))
    }

}