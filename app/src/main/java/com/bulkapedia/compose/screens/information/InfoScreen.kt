package com.bulkapedia.compose.screens.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.navigation.ToCATEGORY_MANAGE
import com.bulkapedia.compose.navigation.ToDASHBOARD
import com.bulkapedia.compose.navigation.ToDEV_CHAT
import com.bulkapedia.compose.navigation.ToHEROES_INFO
import com.bulkapedia.compose.navigation.ToHERO_INFO
import com.bulkapedia.compose.navigation.ToINFO
import com.bulkapedia.compose.navigation.ToMANAGE_HEROES_INFO
import com.bulkapedia.compose.navigation.ToMAP
import com.bulkapedia.compose.navigation.ToMAPS
import com.bulkapedia.compose.navigation.ToMECHANIC
import com.bulkapedia.compose.navigation.ToMECHANICS
import com.bulkapedia.compose.navigation.ToSETTINGS
import com.bulkapedia.compose.navigation.ToUSERS_SETS
import com.bulkapedia.compose.data.repos.categories.Category
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.stringToResource
import kotlinx.coroutines.launch

@Composable
fun InfoListNav() {
    Navigation(Destinations.INFO, listOf(
        ToINFO, ToDEV_CHAT, ToSETTINGS, ToDASHBOARD,
        /** Dashboard **/
        ToCATEGORY_MANAGE, ToUSERS_SETS, ToMANAGE_HEROES_INFO,
        /** Maps category **/
        ToMAPS, ToMAP,
        /** HeroInfo category **/
        ToHEROES_INFO, ToHERO_INFO,
        /** Mechanics category **/
        ToMECHANICS, ToMECHANIC,
    ))
}

@Composable
fun InfoScreen(viewModel: InfoViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val scope = rememberCoroutineScope()
    val categories by viewModel.categoryFlow.collectAsState()
    ScreenView(title = "Выберите категорию") {
        TextSnackbar { action ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
            ) {
                items(categories) { cat ->
                    CategoryCard(cat) {
                        when (cat.enabled) {
                            true -> navController.navigate(cat.destination)
                            else -> scope.launch { action.showSnackbar("Скоро откроется") }
                        }
                    }
                }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.fetchCategories()
        onDispose { viewModel.dispose() }
    }
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp),
        onClick = onClick
    ) {
        Row (
            modifier = Modifier.fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(stringToResource(category.icon)),
                contentDescription = "category_icon",
                tint = Teal200,
                modifier = Modifier.size(50.dp)
            )
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = category.title,
                    color = Teal200,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp)
                )
                Text(
                    text = category.subTitle,
                    color = Teal,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}
