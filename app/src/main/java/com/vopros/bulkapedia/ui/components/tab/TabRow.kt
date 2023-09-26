package com.vopros.bulkapedia.ui.components.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Text
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> TabRowWithPager(
    pages: List<Tab>,
    content: List<T>,
    pattern: @Composable (T) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pager = rememberPagerState { pages.size }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        androidx.compose.material3.TabRow(
            selectedTabIndex = pager.currentPage,
        ) {
            List(pages.size) { i ->
                Tab(
                    selected = pager.currentPage == i,
                    onClick = { scope.launch { pager.animateScrollToPage(i) } }
                ) {
                    Text(resource = pages[i].title)
                }
            }
        }
        when (content) {
            emptyList<T>() -> HCenterBox { Text(resource = R.string.empty_sets) }
            else -> HorizontalPager(state = pager) { pattern(content[it]) }
        }
    }
}