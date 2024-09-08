package com.example.recipes.presentation.models

import com.example.recipes.data.db.entities.MenuEntity
import com.example.recipes.data.db.entities.MenuWithRecipes

data class Menu(
    val title: String,
    val photo: String,
    val listOfRecipe: List<Recipe>,
)

fun Menu.toMenuEntity(): MenuEntity {
    return MenuEntity(
        title = title,
        photo = photo
    )
}

fun MenuWithRecipes.toMenu(): Menu {
    return Menu(
        title = menu.title,
        photo = menu.photo,
        listOfRecipe = recipes.map { it.toRecipe() }
    )
}