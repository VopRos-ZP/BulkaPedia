package vopros.bulkapedia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme
import vopros.bulkapedia.utils.secondsToTime

@Preview
@Composable
fun ReceiverItem_Preview() {
    BulkapediaTheme {
        Surface(color = BulkaPediaTheme.colors.primaryDark) {
            Column {
                ReceiverItem(text = "Hello", date = secondsToTime(Timestamp.now().seconds), author = "VopRos") {}
                SendItem(text = "Hello", date = secondsToTime(Timestamp.now().seconds)) {}
            }
        }
    }
}

@Composable
fun ReceiverItem(text: String, date: String, author: String, image: String = "", onContextMenu: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .pointerInput(Unit) { detectTapGestures(onLongPress = { onContextMenu() }) },
        horizontalAlignment = Alignment.Start
    ) {
        Text(title = author, color = BulkaPediaTheme.colors.tintColor, fontSize = 12.sp)
        Column (
            modifier = Modifier
                .background(
                    BulkaPediaTheme.colors.primary, RoundedCornerShape(
                        topStart = 15.dp, topEnd = 15.dp, bottomEnd = 15.dp
                    )
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(title = text, color = BulkaPediaTheme.colors.white)
            if (image.isNotEmpty()) {
                Image(url = image)
            }
        }
        Text(
            title = date,
            color = BulkaPediaTheme.colors.white,
            fontSize = 12.sp
        )
    }
}

@Composable
fun SendItem(text: String, date: String, image: String = "", onContextMenu: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .pointerInput(Unit) { detectTapGestures(onLongPress = { onContextMenu() }) },
        horizontalAlignment = Alignment.End
    ) {
        Column (
            modifier = Modifier
                .background(
                    BulkaPediaTheme.colors.tintColor, RoundedCornerShape(
                        topStart = 20.dp, topEnd = 15.dp, bottomStart = 20.dp
                    )
                )
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(title = text, color = BulkaPediaTheme.colors.white)
            if (image.isNotEmpty()) {
                Image(url = image)
            }
        }
        Text(
            title = date,
            color = BulkaPediaTheme.colors.white,
            fontSize = 12.sp
        )
    }
}
