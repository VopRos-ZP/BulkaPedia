package vopros.bulkapedia.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

lateinit var resourceManager: ResourceManager

fun iconNameToVector(name: String): ImageVector {
    return when (name) {
        "man" -> Icons.Default.Person
        "info" -> Icons.Default.Info
        "maps" -> Icons.Default.Map
        "mechanics" -> Icons.Default.Construction
        else -> Icons.Default.Android
    }
}

fun secondsToTime(sec: Long): String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(sec))