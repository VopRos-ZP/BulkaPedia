@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.screens.CustomIndicator
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ITabRow(
    pagerState: PagerState,
    tabs: @Composable @UiComposable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(50.dp),
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
