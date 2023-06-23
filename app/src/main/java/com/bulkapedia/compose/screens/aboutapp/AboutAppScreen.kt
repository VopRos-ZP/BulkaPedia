package com.bulkapedia.compose.screens.aboutapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun AboutAppScreen() {
    // Toolbar
    ScreenView(title = "О приложении", showBack = true) {

    }
    // UI
    Box(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(fraction = 0.923f)
            .padding(horizontal = 20.dp)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
            .border(2.dp, Teal200, RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        Text(
            text = annotatedAboutThisApp(),
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun annotatedAboutThisApp(): AnnotatedString {
    return buildAnnotatedString {

    }
}
