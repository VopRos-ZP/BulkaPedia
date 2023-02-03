@file:Suppress("FunctionName")

package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.ui.theme.*
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview(showBackground = true)
@Composable
fun STextPreview() {
    Box(modifier = Modifier.fillMaxSize().background(PrimaryDark)) {
        SendTextMessage(text = "Мега-супер-дупер-ультра текст", date = "17.06.2005 16:05:15")
    }
}

@Preview(showBackground = true)
@Composable
fun RTextPreview() {
    Box(modifier = Modifier.fillMaxSize().background(PrimaryDark)) {
        ReceiverTextMessage(text = "Мега-супер-дупер-ультра текст", date = "17.06.2005 16:05:15", "VopRos")
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SendTextMessage(text: String, date: String, image: String = "") {
    Column (
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalAlignment = Alignment.End
    ) {
        Column (
            modifier = Modifier
                .background(Teal, RoundedCornerShape(
                    topStart = 20.dp, topEnd = 15.dp, bottomStart = 20.dp
                ))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text = text, color = PrimaryDark)
            if (image.isNotEmpty()) {
                GlideImage(
                    model = image,
                    contentDescription = image
                )
            }
        }
        Text(
            text = date,
            color = Teal,
            fontSize = 12.sp
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReceiverTextMessage(text: String, date: String, author: String, image: String = "") {
    Column (
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = author, color = Teal)
        Column (
            modifier = Modifier
                .background(Teal, RoundedCornerShape(
                    topStart = 15.dp, topEnd = 20.dp, bottomEnd = 20.dp
                ))
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text = text, color = PrimaryDark)
            if (image.isNotEmpty()) {
                GlideImage(
                    model = image,
                    contentDescription = image
                )
            }
        }
        Text(
            text = date,
            color = Teal,
            fontSize = 12.sp
        )
    }
}
