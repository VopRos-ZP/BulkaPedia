package com.vopros.bulkapedia.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Image(
    url: String,
    isLoading: Boolean = true,
    isError: Boolean = true
) {
    GlideImage(
        model = url,
        contentDescription = null,
        loading = if (isLoading) placeholder { CircularProgressIndicator() } else null,
        failure = if (isError) placeholder { Text("Error loading image", color = Color.Red) } else null
    )
}