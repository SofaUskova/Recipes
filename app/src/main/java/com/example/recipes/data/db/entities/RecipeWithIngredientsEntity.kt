package com.example.recipes.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithIngredientsEntity(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_id"
    )
    val ingredients: List<IngredientEntity>
)
