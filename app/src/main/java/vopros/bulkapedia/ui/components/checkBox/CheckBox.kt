package vopros.bulkapedia.ui.components.checkBox

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.theme.BulkaPediaTheme

@Composable
fun CheckBox(
    state: MutableState<Boolean>,
    @StringRes text: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = BulkaPediaTheme.colors.tintColor,
                uncheckedColor = BulkaPediaTheme.colors.tintColor,
                checkmarkColor = BulkaPediaTheme.colors.primaryDark
            )
        )
        Text(resource = text)
    }
}