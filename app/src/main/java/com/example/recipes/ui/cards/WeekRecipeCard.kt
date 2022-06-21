package com.example.recipes.ui.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.R
import com.example.recipes.data.Recipe
import com.example.recipes.ui.utils.bottomPadding6dp
import com.example.recipes.ui.utils.clickableRipple
import com.example.recipes.ui.utils.horizontalPadding
import com.example.recipes.ui.utils.shadowRoundedCornerShape12dp
import com.example.recipes.ui.utils.verticalPadding

@OptIn(ExperimentalMaterialApi::class)
class WeekRecipeCard(private val recipe: Recipe) {

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
                Row(
                    modifier = Modifier.horizontalPadding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        text = recipe.title
                    )
                    Icon(
                        modifier = Modifier.clickableRipple { /*TODO*/ },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close icon"
                    )
                }

                Divider(modifier = Modifier.verticalPadding(16.dp))

                Text(
                    fontSize = 14.sp,
                    modifier = Modifier.horizontalPadding(16.dp),
                    text = recipe.ingredients
                )
            }
        }
    }

}