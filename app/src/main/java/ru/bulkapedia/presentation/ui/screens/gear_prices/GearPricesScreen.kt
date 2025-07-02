package ru.bulkapedia.presentation.ui.screens.gear_prices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.bulkapedia.R
import ru.bulkapedia.presentation.ui.components.BackTopAppBar
import ru.bulkapedia.presentation.ui.components.Loader
import ru.bulkapedia.presentation.ui.theme.Celestial
import ru.bulkapedia.presentation.ui.theme.Common
import ru.bulkapedia.presentation.ui.theme.Epic
import ru.bulkapedia.presentation.ui.theme.Immortal
import ru.bulkapedia.presentation.ui.theme.Legendary
import ru.bulkapedia.presentation.ui.theme.Mythic
import ru.bulkapedia.presentation.ui.theme.Rare
import ru.bulkapedia.presentation.ui.theme.Stellar
import ru.bulkapedia.presentation.ui.theme.Supreme
import ru.bulkapedia.presentation.ui.theme.Ultimate

@Composable
fun GearPricesScreen(
    viewModel: GearPricesViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is GearPricesSideEffect.OnBackClick -> onBackClick()
        }
    }

    val rankNames = stringArrayResource(R.array.ranks)
    val rankColors = listOf(
        Common, Rare, Epic, Legendary, Mythic,
        Supreme, Ultimate, Celestial, Stellar, Immortal
    )
    val width = 110.dp
    Scaffold(
        topBar = {
            BackTopAppBar("Копии снаряжения") {
                viewModel.onBackClick()
            }
        }
    ) { innerPadding ->
        if (state.isLoading) {
            Loader()
        } else {
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GearPriceTextItem(
                                text = "Ранг",
                                modifier = Modifier.width(width),
                            )
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.common_gears_icon),
                                    contentDescription = "common_gears"
                                )
                            }
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.nuts_icon),
                                    contentDescription = "nuts"
                                )
                            }
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.rare_gears_icon),
                                    contentDescription = "rare_gears"
                                )
                            }
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.nuts_icon),
                                    contentDescription = "nuts"
                                )
                            }
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.personal_gears_icon),
                                    contentDescription = "personal_gears"
                                )
                            }
                            Box(
                                modifier = Modifier.width(width),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.nuts_icon),
                                    contentDescription = "nuts"
                                )
                            }
                        }
                        List(state.gearPrices.size) { i ->
                            val gearPrice = state.gearPrices[i]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(rankColors[i])
                                    .padding(vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GearPriceTextItem(
                                    text = rankNames[i],
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.gearsCommon}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.priceCommon}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.gearsRare}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.priceRare}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.gearsPersonal}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                                GearPriceTextItem(
                                    text = "${gearPrice.pricePersonal}",
                                    modifier = Modifier.width(width),
                                    color = Color.White
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GearPriceTextItem(
                                text = "Сумма для 1",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsCommon }}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.priceCommon }}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsRare }}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.priceRare }}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsPersonal }}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.pricePersonal }}",
                                modifier = Modifier.width(width),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GearPriceTextItem(
                                text = "Сумма всех копий по слотам",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsCommon } * 30}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.priceCommon } * 30}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsRare } * 30}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.priceRare } * 30}",
                                modifier = Modifier.width(width),
                            )
                            val heroCount = 31
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.gearsPersonal } * 6 * heroCount}",
                                modifier = Modifier.width(width),
                            )
                            GearPriceTextItem(
                                text = "${state.gearPrices.sumOf { it.pricePersonal } * 6 * heroCount}",
                                modifier = Modifier.width(width),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GearPriceTextItem(
    text: String,
    modifier: Modifier,
    color: Color = Color.Unspecified,
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 10.sp,
        color = color
    )
}