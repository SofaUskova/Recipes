package com.example.recipes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipes.R
import com.example.recipes.ui.utils.verticalPadding

class RecipeScreen {

    @Composable
    fun RenderScreen() {

        Scaffold(
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back"
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = "edit"
                    )
                }
            }
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "recipe photo"
                )
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                ) {
                    Text(
                        fontSize = 22.sp,
                        fontWeight = W500,
                        text = "Ленивый хачапури"
                    )
                    Text(
                        modifier = Modifier.verticalPadding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = W500,
                        text = "Ингридиенты:"
                    )
                    Text(
                        modifier = Modifier.verticalPadding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = W500,
                        text = "Способ приготавления:"
                    )
                    Text(
                        fontSize = 14.sp,
                        text = "Хорошо перемешать все ингредиенты. Жарить на большом кол-ве масла, под крышкой с двух сторон."
                    )
                }
            }
        }

    }

}