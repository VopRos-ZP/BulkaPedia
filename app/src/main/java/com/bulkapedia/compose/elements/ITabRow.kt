package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ITabRow(
    pagerState: PagerState,
    tabs: @Composable @UiComposable () -> Unit
) {
    ITabRow(pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp)
        , tabs
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ITabRow(
    pagerState: PagerState,
    marginVertical: Dp = 0.dp,
    tabs: @Composable @UiComposable () -> Unit
) {
    ITabRow(pagerState, marginVertical, marginVertical, tabs)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ITabRow(
    pagerState: PagerState,
    marginTop: Dp = 0.dp,
    marginBottom: Dp = 0.dp,
    tabs: @Composable @UiComposable () -> Unit
) {
    ITabRow(pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = marginTop, bottom = marginBottom)
            .height(50.dp)
        , tabs
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ITabRow(
    pagerState: PagerState,
    modifier: Modifier,
    tabs: @Composable @UiComposable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 10.dp,
        shape = RoundedCornerShape(50.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200)
    ) {
        TabRow(
            modifier = Modifier.fillMaxHeight(),
            selectedTabIndex = pagerState.currentPage,
            indicator = @Composable {
                CustomIndicator(
                    tabPositions = it,
                    pagerState = pagerState
                )
            },
            contentColor = PrimaryDark,
            backgroundColor = PrimaryDark,
            tabs = tabs
        )
    }
}
