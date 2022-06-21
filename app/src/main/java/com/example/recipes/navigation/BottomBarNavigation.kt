package com.example.recipes.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipes.screens.CreateRecipe
import com.example.recipes.screens.RecipesList
import com.example.recipes.screens.ShoppingList
import com.example.recipes.screens.WeekList

class BottomBarNavigation {

    val items = listOf(
        BottomBarRoutes.Recipe,
        BottomBarRoutes.CreateRecipe,
        BottomBarRoutes.WeekList,
        BottomBarRoutes.ShoppingList
    )

    @Composable
    fun BottomBarNavHost(navController: NavHostController, innerPadding: PaddingValues) {
        NavHost(
            navController = navController,
            startDestination = BottomBarRoutes.Recipe.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarRoutes.Recipe.route) {
                RecipesList().RenderScreen()
            }
            composable(BottomBarRoutes.CreateRecipe.route) {
                CreateRecipe().RenderScreen()
            }
            composable(BottomBarRoutes.WeekList.route) {
                WeekList().RenderScreen()
            }
            composable(BottomBarRoutes.ShoppingList.route) {
                ShoppingList().RenderScreen()
            }
        }
    }

}