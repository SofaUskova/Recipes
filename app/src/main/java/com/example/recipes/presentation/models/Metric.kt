package com.example.recipes.presentation.models

enum class Metric(val abbreviation: String) {
    GRAM("г"),
    MILLILITER("мл"),
    TABLESPOON("ст.л"),
    TEASPOON("ч.л")
}

fun abbreviationOf(metric: String): Metric {
    return Metric.entries.find { it.abbreviation == metric } ?: Metric.GRAM
}