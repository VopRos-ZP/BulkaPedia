package com.bulkapedia.compose.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.BulkaPediaTheme
import com.bulkapedia.compose.ui.theme.LocalToolbarContext
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.util.stringToResource


@Composable
fun CommentsButton(
    set: UserSet,
    isDisable: Boolean
) {
    val nc = LocalToolbarContext.current.navController
    SetButton(id = R.drawable.comment) {
        if (!isDisable) {
            nc.navigate("${Destinations.COMMENTS}/${set.userSetId}")
        }
    }
}

@Composable
fun ProfileButton(
    set: UserSet,
    isDisable: Boolean
) {
    val nc = LocalToolbarContext.current.navController
    SetButton(id = R.drawable.person) {
        if (!isDisable) {
            nc.navigate("${Destinations.VISIT_PROFILE}/${set.from}")
        }
    }
}

@Composable
fun SettingsButton(
    expanded: MutableState<Boolean>,
    set: UserSet,
    isDisable: Boolean,
    onDelete: (UserSet) -> Unit = {}
) {
    val nc = LocalToolbarContext.current.navController
    VCenteredBox {
        SetButton(id = R.drawable.settings) {
            if (!isDisable) {
                expanded.value = expanded.value.not()
            }
        }
        SetSettingsMenu(expanded = expanded, set = set,
            onEditClick = { set ->
                nc.navigate("${Destinations.CREATE_SET}/${set.hero}/${set.from}?setId=${set.userSetId}")
            },
            onDeleteClick = onDelete
        )
    }
}

@Composable
fun SetButton(
    string: String,
    onClick: () -> Unit
) {
    SetButton(id = stringToResource(string), onClick)
}

@Composable
fun SetButton(
    id: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "comments",
        colorFilter = ColorFilter.tint(Teal200),
        modifier = Modifier
            .size(40.dp)
            .background(PrimaryDark, BulkaPediaTheme.shapes.round10)
            .border(1.dp, Teal200, BulkaPediaTheme.shapes.round10)
            .clickable(onClick)
            .padding(5.dp)
    )
}

