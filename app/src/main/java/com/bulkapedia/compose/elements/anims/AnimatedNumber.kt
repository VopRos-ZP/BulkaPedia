package com.bulkapedia.compose.elements.anims

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.bulkapedia.compose.ui.theme.Teal200


/**
 * Shows animated addition and removing numbers
 * @param targetState is mutating number
 * @param append is text what append in start example: `"$append $targetState"`
 *
 * **/
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedNumber(targetState: Int, append: String = "") {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            if (targetState > initialState) {
                slideInVertically { height -> height } + fadeIn() with
                        slideOutVertically { height -> -height } + fadeOut()
            } else {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }.using(SizeTransform(clip = false))
        }, label = ""
    ) {
        Text(
            text = if (append.isEmpty()) "$targetState" else "$append $targetState",
            color = Teal200
        )
    }
}