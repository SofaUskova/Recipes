package com.example.recipes.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
fun Modifier.horizontalPadding(value: Dp) = padding(start = value, end = value)

@Stable
fun Modifier.verticalPadding(value: Dp) = padding(top = value, bottom = value)

@Stable
fun Modifier.topPadding16dp() = padding(top = 16.dp)

@Stable
fun Modifier.bottomPadding6dp() = padding(bottom = 6.dp)

@Stable
fun Modifier.shadowRoundedCornerShape12dp() = shadow(6.dp, RoundedCornerShape(12.dp))

@Stable
fun Modifier.clickableRipple(onClick: () -> Unit) = composed {
    clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = false),
        onClick = onClick
    )
}