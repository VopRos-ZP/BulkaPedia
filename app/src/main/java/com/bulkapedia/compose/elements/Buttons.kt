package com.bulkapedia.compose.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.R
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.ui.theme.BulkaPediaTheme
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.util.stringToResource

@Composable
fun CommentsButton(
    set: UserSet,
    isDisable: Boolean
) {
    val nc = LocalNavController.current
    SetButton(id = R.drawable.comment) {
        if (!isDisable) {
            nc.navigate("${Destinations.COMMENTS}/${set.id}")
        }
    }
}

@Composable
fun ProfileButton(
    set: UserSet,
    isDisable: Boolean
) {
    val nc = LocalNavController.current
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
    val nc = LocalNavController.current
    VCenteredBox {
        SetButton(id = R.drawable.settings) {
            if (!isDisable) {
                expanded.value = expanded.value.not()
            }
        }
        SetSettingsMenu(expanded = expanded, set = set,
            onEditClick = { set ->
                nc.navigate("${Destinations.CREATE_SET}/${set.hero}/${set.from}?setId=${set.id}")
            },
            onDeleteClick = onDelete
        )
    }
}

@Composable
fun SetButton(string: String, onClick: () -> Unit) {
    SetButton(id = stringToResource(string), onClick)
}

@Composable
fun SetButton(id: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "comments",
        colorFilter = ColorFilter.tint(Teal200),
        modifier = Modifier
            .size(40.dp)
            .background(PrimaryDark, RoundedCornerShape(10.dp))
            .border(1.dp, Teal200, RoundedCornerShape(10.dp))
            .clickable(onClick)
            .padding(5.dp)
    )
}

