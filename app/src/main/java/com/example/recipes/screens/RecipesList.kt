package com.example.recipes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipes.data.StubData
import com.example.recipes.ui.cards.RecipeCard
import com.example.recipes.ui.theme.PrimaryTransparent20

class RecipesList {

    @Composable
    fun RenderScreen() {
        LazyColumn(
            modifier = Modifier.background(PrimaryTransparent20),
            contentPadding = PaddingValues(6.dp)
        ) {
            items(StubData.listRecipes) { recipe ->
                RecipeCard(recipe).Render()
            }
        }
    }

}