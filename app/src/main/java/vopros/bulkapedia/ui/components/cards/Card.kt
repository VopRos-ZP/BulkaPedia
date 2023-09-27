package vopros.bulkapedia.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.theme.BulkapediaTheme

@Preview(showBackground = true)
@Composable
fun WidthCard_Preview() {
    Card {
        Text(resource = R.string.arnie)
        Image(url = "https://firebasestorage.googleapis.com/v0/b/bulkapedia-3b45a.appspot.com/o/heroes%2Fscouts%2Falice_icon.jpg?alt=media&token=2ee3ebef-ac18-4bb3-b550-4d64f701a479")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedCard(
    onClick: () -> Unit = {},
    spacedBy: Dp = 5.dp,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material.Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        border = BorderStroke(2.dp, MaterialTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = horizontal,
            verticalArrangement = Arrangement.spacedBy(spacedBy),
            content = content
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(
    onClick: () -> Unit = {},
    spacedBy: Dp = 5.dp,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material.Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = horizontal,
            verticalArrangement = Arrangement.spacedBy(spacedBy),
            content = content
        )
    }
}
