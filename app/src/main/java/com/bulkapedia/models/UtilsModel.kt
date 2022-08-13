package com.bulkapedia.models

import androidx.drawerlayout.widget.DrawerLayout
import com.bulkapedia.MainActivity

data class UtilsModel (
    val main: MainActivity,
    val drawer: DrawerLayout?
)