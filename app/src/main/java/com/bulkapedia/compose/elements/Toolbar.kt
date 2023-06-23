package com.bulkapedia.compose.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.elements.topbar.ToolbarViewModel
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import java.util.*

@Composable
fun Toolbar() {
    val navController = LocalNavController.current
    val viewModel = LocalTopBar.current

    val backState = viewModel.showBackFlow.collectAsState()
    val titleState = viewModel.titleFlow.collectAsState()
    TopAppBar(
        backgroundColor = PrimaryDark,
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        if (backState.value) {
            VCenteredBox {
                Image(
                    painterResource(id = R.drawable.backspace),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable { navController.navigateUp() },
                    contentDescription = "back",
                    colorFilter = ColorFilter.tint(Teal200)
                )
            }
        }
        VCenteredBox {
            Text(
                modifier = Modifier.padding(start = 20.dp),
                text = titleState.value.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                color = Teal200,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        VCenteredBox(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.settings),
                contentDescription = "settings",
                colorFilter = ColorFilter.tint(Teal200),
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.navigate(Destinations.SETTINGS) { launchSingleTop = true } }
            )
        }
    }
}
