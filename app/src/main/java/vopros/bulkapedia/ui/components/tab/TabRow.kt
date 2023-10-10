package vopros.bulkapedia.ui.components.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Text
import kotlinx.coroutines.launch
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

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
        androidx.compose.material.TabRow(
            modifier = Modifier.padding(horizontal = 20.dp),
            selectedTabIndex = pager.currentPage,
            backgroundColor = BulkaPediaTheme.colors.primary
        ) {
            List(pages.size) { i ->
                Tab(
                    modifier = Modifier.padding(vertical = 10.dp),
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