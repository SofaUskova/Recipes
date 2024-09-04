package com.example.recipes.ui.models

import kotlin.random.Random

sealed interface RecipeItem {
    val id: Long
    val type: RecipeItemType
}

data class RecipeTextItem(
    override val id: Long = Random.nextLong(),
    override val type: RecipeItemType,
    var text: String = "",
    val hint: String,
    var isEmpty: Boolean = false
) : RecipeItem {

    fun recheckValues(): Boolean = text.isEmpty()
}

class RecipeButtonItem(
    override val id: Long = Random.nextLong(),
    override val type: RecipeItemType = RecipeItemType.BUTTON,
    val text: String,
    val action: () -> Unit,
) : RecipeItem

data class RecipeImageItem(
    override val id: Long = Random.nextLong(),
    override val type: RecipeItemType = RecipeItemType.IMAGE,
    var imageUrl: String?,
    val action: () -> Unit
) : RecipeItem

data class RecipeIngredientItem(
    override val id: Long = Random.nextLong(),
    override val type: RecipeItemType = RecipeItemType.INGREDIENT,
    var text: String = "",
    var quantity: Float = 0f,
    var metric: Metric = Metric.GRAM,
    var isEmpty: Boolean = false
) : RecipeItem {

    fun recheckValues(): Boolean = text.isEmpty() || quantity == 0f
}

fun RecipeIngredientItem.toIngredient(): Ingredient {
    return Ingredient(
        id = id,
        text = text,
        quantity = quantity,
        metric = metric
    )
}