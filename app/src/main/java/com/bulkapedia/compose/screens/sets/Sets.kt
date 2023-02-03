@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.sets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.R
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.elements.CommentsButton
import com.bulkapedia.compose.elements.ProfileButton
import com.bulkapedia.compose.elements.SettingsButton
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.util.stringToResource
import kotlinx.coroutines.launch

@Composable
fun SetInProfileCard(
    set: UserSet,
    see: Boolean = false,
    disableComments: Boolean = false,
    disableSettings: Boolean = false,
    onDelete: (UserSet) -> Unit = {}
) {
    val expanded = remember { mutableStateOf(false) }
    SetCard(set = set) {
        // author
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            HCenteredBox {
                Image(
                    painter = painterResource(stringToResource(set.hero.plus("_icon"))),
                    contentDescription = set.hero,
                    modifier = Modifier.height((75 * 3 / 2).dp)
                )
            }
        }
        // likes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) { CenteredBox { LikeRow(set) } }
        // buttons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                VCenteredBox(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxHeight()
                ) {
                    CommentsButton(set, disableComments)
                }
                if (see) {
                    VCenteredBox { ProfileButton(set, disableSettings) }
                } else {
                    SettingsButton(expanded, set, disableSettings, onDelete)
                }
            }
        }
    }
}

@Composable
fun SetTabCard(
    set: UserSet,
    see: Boolean = false,
    disableComments: Boolean = false,
    disableSettings: Boolean = false,
    onDelete: (UserSet) -> Unit = {}
) {
    val expanded = remember { mutableStateOf(false) }
    SetCard(set) {
        // author
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            CenteredBox {
                Text(text = set.from, color = Teal200)
            }
        }
        // likes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            CenteredBox { LikeRow(set) }
        }
        // buttons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                VCenteredBox(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .fillMaxHeight()
                ) {
                    CommentsButton(set, disableComments)
                }
                if (see) {
                    VCenteredBox { ProfileButton(set, disableSettings) }
                } else {
                    SettingsButton(expanded, set, disableSettings, onDelete)
                }
            }
        }
    }
}

@Composable
fun SetCard(
    set: UserSet,
    column: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        border = BorderStroke(2.dp, Teal200)
    ) {
        Row {
            Set(gears = set.gears)
            Column (
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                content = column
            )
        }
    }
}

@Composable
fun Set(
    padding: PaddingValues = PaddingValues(start = 20.dp, top = 20.dp, bottom = 20.dp),
    gears: Map<GearCell, String>,
    onHeadClick: () -> Unit = {},
    onBodyClick: () -> Unit = {},
    onArmClick: () -> Unit = {},
    onLegClick: () -> Unit = {},
    onDecorClick: () -> Unit = {},
    onDeviceClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier.padding(padding),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { // head / body
            GearImage(image = gears[GearCell.HEAD] ?: "", default = R.drawable.empty_head, onHeadClick)
            GearImage(image = gears[GearCell.BODY] ?: "", default = R.drawable.empty_body, onBodyClick)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { // arm / leg
            GearImage(image = gears[GearCell.ARM] ?: "", default = R.drawable.empty_arm, onArmClick)
            GearImage(image = gears[GearCell.LEG] ?: "", default = R.drawable.empty_leg, onLegClick)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) { // decor / device
            GearImage(image = gears[GearCell.DECOR] ?: "", default = R.drawable.empty_decor, onDecorClick)
            GearImage(image = gears[GearCell.DEVICE] ?: "", default = R.drawable.empty_device, onDeviceClick)
        }
    }
}

@Composable
fun GearImage(
    image: String,
    default: Int = R.drawable.empty_head,
    onClick: () -> Unit
) {
    val img = if (stringToResource(image) == 0) default else stringToResource(image)
    Image(
        painter = painterResource(id = img),
        contentDescription = image,
        modifier = Modifier
            .size(size = 75.dp)
            .padding(end = 10.dp)
            .clickable(onClick),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun LikeRow(set: UserSet) {
    val store = com.bulkapedia.compose.DataStore(LocalContext.current)
    val emailState = store.getEmail.collectAsState(initial = "")
    val nickname = store.getNickname.collectAsState(initial = "")
    val liked = set.userLikeIds.contains(emailState.value)
    val scope = rememberCoroutineScope()
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = if (liked) R.drawable.liked else R.drawable.unliked),
            contentDescription = "like",
            modifier = Modifier.size(40.dp)
                .clickable {
                    if (set.from != nickname.value) {
                        if (liked) {
                            set.likes--
                            set.userLikeIds.remove(emailState.value)
                        } else {
                            set.likes++
                            set.userLikeIds.add(emailState.value!!)
                        }
                        scope.launch { Database().addSet(set) }
                    }
                }
                .padding(5.dp)
        )
        VCenteredBox (Modifier.size(40.dp)) {
            Text(text = "${set.likes}", color = Teal200)
        }
    }
}

@Composable
fun DarkButton(id: Int, onClick: () -> Unit) {
    DarkButton(id, Modifier, onClick)
}

@Composable
fun DarkButton(
    id: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = "comments",
        colorFilter = ColorFilter.tint(Teal200),
        modifier = modifier
            .size(40.dp)
            .background(Color.Transparent, RoundedCornerShape(10.dp))
            .border(1.dp, Teal200, RoundedCornerShape(10.dp))
            .clickable(onClick)
            .padding(5.dp)
    )
}
