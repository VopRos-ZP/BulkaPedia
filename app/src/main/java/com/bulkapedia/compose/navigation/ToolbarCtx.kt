package com.bulkapedia.compose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController

class ToolbarCtx (
    private val data: MutableLiveData<ToolbarData>,
    var navController: NavController
) {

    @Composable
    fun observeAsState(): State<ToolbarData?> {
        return data.observeAsState()
    }

    fun onBackPressed() {
        navController.navigateUp()
    }

    fun setTitle(title: String) {
        setData(title, showBackButton = false)
    }

    fun setShowBackButton(showBackButton: Boolean) {
        setData(this.data.value?.title ?: "", showBackButton)
    }

    fun setVisibilitySettings(isVisibleSettings: Boolean) {
        setData(
            title = data.value?.title ?: "",
            showBackButton = data.value?.showBackButton ?: false,
            isVisibleSettings = isVisibleSettings
        )
    }

    fun setData(title: String, showBackButton: Boolean) {
        setData(title, showBackButton, isVisibleSettings = true)
    }

    fun setData(title: String, showBackButton: Boolean, isVisibleSettings: Boolean) {
        updateData(ToolbarData(title, showBackButton, isVisibleSettings))
    }

    private fun updateData(data: ToolbarData) {
        this.data.postValue(this.data.value?.copy(
            title = data.title,
            showBackButton = data.showBackButton,
            isVisibleSettings = data.isVisibleSettings
        ))
    }

}

data class ToolbarData(
    var title: String,
    var showBackButton: Boolean = false,
    var isVisibleSettings: Boolean = true
)