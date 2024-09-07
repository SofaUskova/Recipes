package com.example.recipes.presentation.ui.common

fun String?.quantityToFloat(): Float {
    return this
        ?.run { if (isNotEmptyOrBlank()) toFloat() else 0f }
        ?: 0f
}

fun String?.timeToFloat(): Float {
    return this?.removeSuffix("h")?.toFloat() ?: 0f
}

fun String.isNotEmptyOrBlank(): Boolean = isNotEmpty() && isNotBlank()
