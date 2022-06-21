package com.example.recipes.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
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