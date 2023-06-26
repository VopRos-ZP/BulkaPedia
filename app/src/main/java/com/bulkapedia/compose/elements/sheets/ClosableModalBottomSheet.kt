package com.bulkapedia.compose.elements.sheets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.elements.OutlinedButton
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.util.HCenteredBox
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ClosableModalBottomSheet(
    sheetContent: @Composable () -> Unit,
    key: Any?, onClose: () -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
        confirmValueChange = {
            if (it == ModalBottomSheetValue.Hidden) { onClose() }
            true
        }
    )
    ModalBottomSheetLayout(
        sheetContent = {
            HCenteredBox {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(text = "Закрыть", color = Color.Red) {
                        scope.launch { sheetState.hide() }
                        onClose()
                    }
                    sheetContent()
                }
            }
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = PrimaryDark,
        sheetElevation = 10.dp
    ) {
        content()
        LaunchedEffect(key) { if (key != null) { sheetState.show() } }
    }
}
