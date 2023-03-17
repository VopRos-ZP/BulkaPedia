@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.HCenteredBox
import com.bulkapedia.compose.data.labels.Stats

@Composable
fun MainTagDialog(
    action: ScreenAction.AddTagAction,
    onSave: (String, Stats) -> Unit
) {
    val hero = remember { action.defHero }
    val showMenu = remember { mutableStateOf(false) }
    val kills = remember { action.defKills }
    val winrate = remember { action.defWR }
    val revives = remember { action.defRevives }
    // UI
    Dialog(onDismissRequest = {/* On touch outside */}) {
        Column (
            modifier = Modifier
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(20.dp)
        ) {
            HCenteredBox {
                Text(text = "Добавить мейн героя", color = Teal200, fontSize = 18.sp)
            }
            // Content
            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                OutlinedTextField(kills, "Килы", shape = RoundedCornerShape(10.dp))
                OutlinedTextField(winrate, "Процент побед", shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(top = 5.dp))
                OutlinedTextField(revives, "Герой воскресил", shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(top = 5.dp))
                OutlinedButton(
                    onClick = { showMenu.value = !showMenu.value },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                    border = BorderStroke(1.dp, Teal200),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 5.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = hero.value, color = Teal200)
                        Icon(
                            if (showMenu.value) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                            contentDescription = "",
                            tint = Teal200
                        )
                    }
                }
                SelectHeroMainMenu(showMenu, hero.value) { selected ->
                    hero.value = selected
                }
            }
            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InRowOutlinedButton(text = "Отмена", color = Color.Red) {
                    action.show.value = false
                }
                InRowOutlinedButton(text = "Сохранить", color = Color.Green) {
                    action.show.value = false
                    onSave.invoke( hero.value,
                        Stats(
                            kills = kills.value.toInt(),
                            winrate = winrate.value.toInt(),
                            revives = revives.value.toInt()
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SelectHeroMainMenu(
    show: MutableState<Boolean>,
    selected: String,
    onSelect: (String) -> Unit
) {
    val heroes = listOf(
        "Арни", "Циклоп", "Искра", "Ураган",
        "Призрак", "Фредди", "Ангел", "Ворон",
        "Блот", "Огонек", "Губитель", "Мираж", "Линкс",
        "Смог", "Драгун", "Бастион", "Берта", "Левиафан",
        "Сталкер", "Док", "Леви", "Сатоши", "Тесс",
    )
    MaterialTheme(
        shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(20.dp))
    ) {
        DropdownMenu(
            modifier = Modifier
                .background(PrimaryDark)
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(4.dp),
            expanded = show.value,
            onDismissRequest = { show.value = false }
        ) {
            heroes.map { hero ->
                DropdownMenuItem(
                    onClick = { show.value = false; onSelect.invoke(hero) },
                    enabled = hero != selected
                ) {
                    Text(text = hero, color = Teal200)
                }
            }
        }
    }
}
