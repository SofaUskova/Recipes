package com.example.recipes.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.data.Recipe
import com.example.recipes.ui.utils.bottomPadding6dp
import com.example.recipes.ui.utils.horizontalPadding
import com.example.recipes.ui.utils.shadowRoundedCornerShape12dp
import com.example.recipes.ui.utils.topPadding16dp
import com.example.recipes.ui.utils.verticalPadding

@OptIn(ExperimentalMaterialApi::class)
class RecipeCard(private val recipe: Recipe) {

    @Composable
    fun Render() {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .bottomPadding6dp()
                .shadowRoundedCornerShape12dp(),
            onClick = { /*todo*/ },
        ) {
            Column(
                modifier = Modifier.verticalPadding(16.dp)
            ) {
                Text(
                    fontSize = 16.sp,
                    fontWeight = W600,
                    modifier = Modifier.horizontalPadding(16.dp),
                    text = recipe.title
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalPadding(16.dp),
                    painter = painterResource(id = recipe.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "recipe image"
                )
                Text(
                    fontSize = 14.sp,
                    modifier = Modifier.horizontalPadding(16.dp),
                    text = recipe.ingredients
                )
                Button(
                    modifier = Modifier
                        .horizontalPadding(16.dp)
                        .topPadding16dp()
                        .align(Alignment.End),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        fontSize = 14.sp,
                        text = "Добавить"
                    )
                }
            }
        }
    }

}