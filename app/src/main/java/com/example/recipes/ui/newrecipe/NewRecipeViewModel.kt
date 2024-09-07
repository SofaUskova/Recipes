package com.example.recipes.ui.newrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.repository.IngredientRepository
import com.example.recipes.repository.RecipeRepository
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.RecipeButtonItem
import com.example.recipes.ui.models.RecipeImageItem
import com.example.recipes.ui.models.RecipeIngredientItem
import com.example.recipes.ui.models.RecipeItem
import com.example.recipes.ui.models.RecipeItemType
import com.example.recipes.ui.models.RecipeTextItem
import com.example.recipes.ui.models.toRecipe
import com.example.recipes.ui.models.toRecipeIngredientItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

    class NewRecipeViewModel @Inject constructor(
    private val repositoryRecipe: RecipeRepository,
    private val repositoryIngredient: IngredientRepository
) : ViewModel() {
    val currentRecipe = MutableLiveData<Recipe?>()

    fun getRecipe(id: Long?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipe = repositoryRecipe.getRecipeById(id ?: -1)
                currentRecipe.postValue(recipe?.toRecipe())
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
                repositoryRecipe.insertOrUpdateRecipe(newRecipe)
                repositoryIngredient.insertOrUpdateIngredient(newRecipe.ingredients, newRecipe.id)
            }
        }.invokeOnCompletion { completeAction() }
    }

}