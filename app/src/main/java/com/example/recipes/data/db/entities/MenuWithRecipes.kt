package com.example.recipes.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class MenuWithRecipes(
    @Embedded val menu: MenuEntity,
    @Relation(
        parentColumn = "title",
        entityColumn = "menu_title"
    )
    val recipes: List<RecipeEntity>
)
