package com.example.recipes.navigation

import androidx.annotation.StringRes
import com.example.recipes.R

sealed class BottomBarRoutes(
    val route: String,
    @StringRes val labelId: Int,
    val iconId: Int
) {
    object Recipe : BottomBarRoutes(
        "recipe",
        R.string.bottom_nav_recipes,
        R.drawable.ic_bottom_nav_receipt
    )

    object CreateRecipe : BottomBarRoutes(
        "create_recipe",
        R.string.bottom_nav_create_recipe,
        R.drawable.ic_bottom_nav_create_recipe
    )

    object WeekList : BottomBarRoutes(
        "week_list",
        R.string.bottom_nav_week_list,
        R.drawable.ic_bottom_nav_week_list
    )

    object ShoppingList : BottomBarRoutes(
        "shopping_list",
        R.string.bottom_nav_shopping_list,
        R.drawable.ic_bottom_nav_shopping_list
    )

}

