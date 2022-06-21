package com.example.recipes.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Stable
fun Modifier.horizontalPadding(value: Dp) = padding(start = value, end = value)

@Stable
fun Modifier.verticalPadding(value: Dp) = padding(top = value, bottom = value)
