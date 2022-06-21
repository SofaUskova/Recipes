package com.example.recipes.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipes.R
import com.example.recipes.ui.utils.horizontalPadding
import com.example.recipes.ui.utils.verticalPadding

@OptIn(ExperimentalMaterialApi::class)
class RecipeListItem {

    @Composable
    fun Render() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .verticalPadding(8.dp)
                .background(Color.White)
                .shadow(4.dp)
                .clip(RoundedCornerShape(12.dp)),
            onClick = { /*todo*/ },
        ) {
            Column(
                modifier = Modifier.verticalPadding(16.dp)
            ) {
                Text(
                    modifier = Modifier.horizontalPadding(16.dp),
                    text = "Ленивый хачапури"
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalPadding(16.dp),
                    painter = painterResource(id = R.drawable.image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "recipe image"
                )
                Text(
                    modifier = Modifier.horizontalPadding(16.dp),
                    text = "яйца, кефир, мука, соль, сыр, масло подсолнечное"
                )
                Button(
                    modifier = Modifier
                        .horizontalPadding(16.dp)
                        .clip(RoundedCornerShape(100.dp)),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Добавить")
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    LazyColumn() {
        repeat(3) {
            item {
                RecipeListItem().Render()
            }
        }
    }
}