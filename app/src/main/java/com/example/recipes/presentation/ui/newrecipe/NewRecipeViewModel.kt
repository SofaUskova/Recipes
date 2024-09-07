package com.example.recipes.presentation.ui.newrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.models.RecipeButtonItem
import com.example.recipes.presentation.models.RecipeImageItem
import com.example.recipes.presentation.models.RecipeIngredientItem
import com.example.recipes.presentation.models.RecipeItem
import com.example.recipes.presentation.models.RecipeItemType
import com.example.recipes.presentation.models.RecipeTextItem
import com.example.recipes.presentation.models.toRecipeIngredientItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewRecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor,
) : ViewModel() {
    val currentRecipe = MutableLiveData<Recipe?>()

    fun getRecipe(id: Long?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipe = interactor.getRecipeById(id ?: -1)
                currentRecipe.postValue(recipe)
            }
        }
    }

    //todo rewrite this
    fun collectNewOrEditRecipeItemsScreen(
        editRecipe: Recipe?,
        addNewEmptyIngredientAction: () -> Unit,
        saveAction: () -> Unit,
        pickImagesAction: () -> Unit
    ): List<RecipeItem> {
        return mutableListOf(
            RecipeImageItem(imageUrl = editRecipe?.photo, action = pickImagesAction),
            RecipeTextItem(
                type = RecipeItemType.NAME,
                hint = "Name",
                text = editRecipe?.name ?: ""
            ),
            RecipeTextItem(
                type = RecipeItemType.TIME,
                hint = "Time",
                text = editRecipe?.formattedTime ?: ""
            ),
        ).apply {
            editRecipe
                ?.ingredients
                ?.run { addAll(map { it.toRecipeIngredientItem() }) }
                ?: addAll(createListOfEmptyIngredient())

            addAll(
                listOf(
                    RecipeButtonItem(text = "Add ingredient", action = addNewEmptyIngredientAction),
                    RecipeTextItem(
                        type = RecipeItemType.RECIPE,
                        hint = "Recipe",
                        text = editRecipe?.recipe ?: ""
                    ),
                    RecipeButtonItem(text = "Save", action = saveAction)
                )
            )
        }
    }

    private fun createListOfEmptyIngredient() = listOf(RecipeIngredientItem())

    fun saveRecipe(newRecipe: Recipe, completeAction: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                interactor.insertOrUpdateFullRecipe(newRecipe, newRecipe.ingredients)
            }
        }.invokeOnCompletion { completeAction() }
    }

}