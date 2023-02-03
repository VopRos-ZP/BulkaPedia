@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.heroes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.bulkapedia.compose.data.HardCode
import com.bulkapedia.compose.data.*
import com.bulkapedia.compose.elements.Tags
import com.bulkapedia.compose.elements.heroesTags
import com.bulkapedia.compose.models.TagViewModel
import com.bulkapedia.compose.models.TagViewState
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.navigation.Navigation
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.navigation.navigate
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.SortType
import com.bulkapedia.compose.util.stringToResource

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HeroesNavList(toolbarCtx: ToolbarCtx) {
    Navigation(
        startDestination = Destinations.HEROES, toolbarCtx,
        screens = listOf(
            ToHEROES, ToDASHBOARD, ToDEV_CHAT, ToCREATE_SET,
            ToSETTINGS, ToSET, ToTOP, ToCOMMENTS, ToVISIT
        )
    )
}

@Composable
fun Heroes(ctx: ToolbarCtx) {
    ctx.observeAsState()
    ctx.setData("Выберите героя", false)
    // UI
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.923f)
            .background(Color.Transparent)
    ) {
        val tagViewModel = TagViewModel()
        val tagViewState = tagViewModel.viewState.observeAsState()
        Tags(heroesTags(), tagViewModel)
        LazyColumn (
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(Color.Transparent, RoundedCornerShape(25.dp))
                .padding(horizontal = 20.dp)
        ) {
            val filteredList = filterList(list = HardCode.Hero.values, tagViewState = tagViewState.value!!)
            items(filteredList) { item ->
                HCenteredBox {
                    HeroCard(item, ctx.navController, filteredList.last() == item)
                }
            }
        }
    }
}

@Composable
fun HeroCard(hero: String, navController: NavController, isLast: Boolean = false) {
    Card (
        modifier = Modifier.padding(bottom = if (isLast) 0.dp else 20.dp),
        shape = RoundedCornerShape(25.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200)
    ) {
        Row (
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = stringToResource(hero)),
                contentDescription = "",
                modifier = Modifier
                    .width(120.dp)
                    .height(150.dp)
            )
            Column (
                Modifier.padding(start = 10.dp),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = { navController.navigate(
                        "${Destinations.SETS}/${hero}") { launchSingleTop = true }
                              },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, Teal200),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = PrimaryDark),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Сеты", color = Teal200, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {
                        navController.navigate(
                            route = "${Destinations.TOP}/${hero}",
                            bundleOf("hero" to hero)) { launchSingleTop = true }
                              },
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(2.dp, Teal200),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = PrimaryDark),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "Топ 100", color = Teal200, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

private fun filterList(list: List<String>, tagViewState: TagViewState): List<String> {
    return when (tagViewState.selected) {
        true -> when (val sortType = tagViewState.sortType) {
            is SortType.ByHero -> list.filter { HardCode.Hero.getType(it) == sortType.type.str() }
            else -> list
        }
        else -> list
    }
}
