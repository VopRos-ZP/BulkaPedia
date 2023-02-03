package com.bulkapedia.compose.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.dp
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.navigation.ToolbarData
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable

@Composable
fun Toolbar(ctx: ToolbarCtx) {
    val viewState: State<ToolbarData?> = ctx.observeAsState()

    Row (
        modifier = Modifier.fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
            .background(PrimaryDark, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .padding(vertical = 10.dp)
    ) {
        if (viewState.value?.showBackButton == true) {
            VCenteredBox {
                Image(
                    painterResource(id = R.drawable.backspace),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable {
                            ctx.setShowBackButton(viewState.value?.showBackButton?.not() == true)
                            ctx.onBackPressed()
                        },
                    contentDescription = "back",
                    colorFilter = ColorFilter.tint(Teal200)
                )
            }
        }
        if (viewState.value?.isVisibleSettings == false) {
            ToolbarTitle(viewState)
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ToolbarTitle(viewState)
                VCenteredBox(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "settings",
                        colorFilter = ColorFilter.tint(Teal),
                        modifier = Modifier
                            .size(40.dp)
                            .clickable { ctx.navController.navigate(Destinations.SETTINGS) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ToolbarTitle(viewState: State<ToolbarData?>) {
    VCenteredBox {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = viewState.value?.title ?: "",
            color = Teal200,
            fontWeight = FontWeight.Bold
        )
    }
}
