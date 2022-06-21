package com.example.recipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipes.data.StubData
import com.example.recipes.ui.cards.RecipeCard
import com.example.recipes.ui.cards.WeekRecipeCard
import com.example.recipes.ui.theme.PrimaryTransparent20

class RecipesList {

    @Composable
    fun RenderScreen() {
        var tabIndex by remember { mutableStateOf(0) }
        val tabTitles = listOf("Все", "Неделя")

        Column {

            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = PrimaryTransparent20,
                contentColor = Color.Black,
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }
                    )
                }
            }

            when (tabIndex) {
                0 -> RecipesListAll()
                1 -> RecipesListWeek()
            }
        }
    }

    @Composable
    fun RecipesListAll() {
        LazyColumn(
            modifier = Modifier.background(PrimaryTransparent20),
            contentPadding = PaddingValues(6.dp)
        ) {
            items(StubData.listRecipes) { recipe ->
                RecipeCard(recipe).Render()
            }
        }
    }

    @Composable
    fun RecipesListWeek() {
        LazyColumn(
            modifier = Modifier.background(PrimaryTransparent20),
            contentPadding = PaddingValues(6.dp)
        ) {
            items(StubData.listRecipes) { recipe ->
                WeekRecipeCard(recipe).Render()
            }
        }
    }

}