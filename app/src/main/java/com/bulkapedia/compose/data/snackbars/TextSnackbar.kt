@file:Suppress("FunctionName")
package com.bulkapedia.compose.data.snackbars

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox

class TextSnackbarAction (
    private val state: SnackbarHostState
) {
    suspend fun showSnackbar(text: String) : SnackbarResult {
        return state.showSnackbar(message = text)
    }
}

@Composable
fun TextSnackbar(
    content: @Composable (TextSnackbarAction) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val action = TextSnackbarAction(scaffoldState.snackbarHostState)
    CenteredBox (
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 10.dp)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(fraction = 0.9f),
            scaffoldState = scaffoldState,
            snackbarHost = {
                SnackbarHost(it) { data ->
                    Snackbar(
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = PrimaryDark,
                        contentColor = Teal200,
                        actionColor = Teal200,
                        snackbarData = data
                    )
                }
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                content.invoke(action)
            }
        }
    }
}
