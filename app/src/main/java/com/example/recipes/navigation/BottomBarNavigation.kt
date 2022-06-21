package com.example.recipes.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipes.screens.CreateRecipe
import com.example.recipes.screens.Recipe
import com.example.recipes.screens.ShoppingList
import com.example.recipes.screens.WeekList

class BottomBarNavigation {

    val items = listOf(
        BottomBarNavigationScreen.Recipe,
        BottomBarNavigationScreen.CreateRecipe,
        BottomBarNavigationScreen.WeekList,
        BottomBarNavigationScreen.ShoppingList
    )

    @Composable
    fun BottomBarNavHost(navController: NavHostController, innerPadding: PaddingValues) {
        NavHost(
            navController = navController,
            startDestination = BottomBarNavigationScreen.Recipe.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomBarNavigationScreen.Recipe.route) {
                Recipe().RenderScreen()
            }
            composable(BottomBarNavigationScreen.CreateRecipe.route) {
                CreateRecipe().RenderScreen()
            }
            composable(BottomBarNavigationScreen.WeekList.route) {
                WeekList().RenderScreen()
            }
            composable(BottomBarNavigationScreen.ShoppingList.route) {
                ShoppingList().RenderScreen()
            }
        }
    }

}