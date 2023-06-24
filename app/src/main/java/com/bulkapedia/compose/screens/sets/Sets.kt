package com.bulkapedia.compose.screens.sets

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.R
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.elements.CommentsButton
import com.bulkapedia.compose.elements.ProfileButton
import com.bulkapedia.compose.elements.SettingsButton
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.util.VCenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.util.stringToResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun SetInProfileCard(
    set: UserSet,
    see: Boolean = false,
    disableComments: Boolean = false,
    disableSettings: Boolean = false,
    onDelete: (UserSet) -> Unit = {}
) {
    val iconDp = (75 * 3 / 2).dp
    SetCard(set, see, disableComments, disableSettings, onDelete, iconDp, 50.dp) {
        HCenteredBox {
            Image(
                painter = painterResource(stringToResource(set.hero.plus("_icon"))),
                contentDescription = set.hero,
                modifier = Modifier.height(iconDp)
            )
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
    val dp = 75.dp
    SetCard(set, see, disableComments, disableSettings, onDelete, dp, dp) {
        Box(modifier = Modifier.fillMaxWidth().height(dp)) {
            CenteredBox { Text(text = set.from, color = Teal200) }
        }
    }
}

@Composable
fun SetCard(
    set: UserSet,
    see: Boolean = false,
    disableComments: Boolean = false,
    disableSettings: Boolean = false,
    onDelete: (UserSet) -> Unit = {},
    icon: Dp,
    height: Dp,
    viewModel: SetTabViewModel = hiltViewModel(),
    content: @Composable BoxScope.(Dp) -> Unit
) {
    val context = LocalContext.current
    val store = DataStore(context)
    val isSign by store.getSign.collectAsState(false)
    val email by store.getEmail.collectAsState("")
    val nickname by store.getNickname.collectAsState("")

    val liked = set.userLikeIds.contains(email)
    val expanded = remember { mutableStateOf(false) }

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
            Column(
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier.fillMaxWidth()) { content(this, icon) }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(height)) {
                    CenteredBox {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            IconButton(onClick = {
                                viewModel.likeUserSet(isSign, nickname, email, set) {
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                }
                            }) {
                                Icon(
                                    painter = painterResource(id = if (liked) R.drawable.liked else R.drawable.unliked),
                                    contentDescription = "like",
                                    tint = Color.Red
                                )
                            }
                            VCenteredBox (Modifier.size(40.dp)) {
                                Text(text = "${set.userLikeIds.size}", color = Teal200)
                            }
                        }
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(height)) {
                    ButtonsRow(expanded, set, see, disableComments, disableSettings, onDelete)
                }
            }
        }
    }
}

@Composable
fun Set(
    padding: PaddingValues = PaddingValues(start = 20.dp, top = 20.dp, bottom = 20.dp),
    gears: Map<GearCell, String>,
    onCellClick: (GearCell) -> Unit = {},
) {
    Column(modifier = Modifier.padding(padding)) {
        Row(verticalAlignment = Alignment.CenterVertically) { // head / body
            GearImage(image = gears[GearCell.HEAD] ?: "") { onCellClick(GearCell.HEAD) }
            GearImage(image = gears[GearCell.BODY] ?: "") { onCellClick(GearCell.BODY) }
        }
        Row(verticalAlignment = Alignment.CenterVertically) { // arm / leg
            GearImage(image = gears[GearCell.ARM] ?: "") { onCellClick(GearCell.ARM) }
            GearImage(image = gears[GearCell.LEG] ?: "") { onCellClick(GearCell.LEG) }
        }
        Row(verticalAlignment = Alignment.CenterVertically) { // decor / device
            GearImage(image = gears[GearCell.DECOR] ?: "") { onCellClick(GearCell.DECOR) }
            GearImage(image = gears[GearCell.DEVICE] ?: "") { onCellClick(GearCell.DEVICE) }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GearImage(
    image: String,
    onClick: () -> Unit
) {
    GlideImage(
        model = image,
        contentDescription = image,
        modifier = Modifier
            .size(size = 75.dp)
            .padding(end = 10.dp)
            .clickable(onClick)
    )
}

@Composable
fun ButtonsRow(
    expanded: MutableState<Boolean>,
    set: UserSet,
    see: Boolean = false,
    disableComments: Boolean = false,
    disableSettings: Boolean = false,
    onDelete: (UserSet) -> Unit = {}
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
