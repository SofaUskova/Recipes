package com.example.recipes.navigation

import androidx.annotation.StringRes
import com.example.recipes.R

sealed class BottomBarNavigationScreen(
    val route: String,
    @StringRes val labelId: Int,
    val iconId: Int
) {
    object Recipe : BottomBarNavigationScreen(
        "recipe",
        R.string.bottom_nav_recipes,
        R.drawable.ic_bottom_nav_receipt
    )

    object CreateRecipe : BottomBarNavigationScreen(
        "create_recipe",
        R.string.bottom_nav_create_recipe,
        R.drawable.ic_bottom_nav_create_recipe
    )

    object WeekList : BottomBarNavigationScreen(
        "week_list",
        R.string.bottom_nav_week_list,
        R.drawable.ic_bottom_nav_week_list
    )

    object ShoppingList : BottomBarNavigationScreen(
        "shopping_list",
        R.string.bottom_nav_shopping_list,
        R.drawable.ic_bottom_nav_shopping_list
    )

}

