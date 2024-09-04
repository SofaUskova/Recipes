package com.example.recipes.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.db.AppDatabase
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.toRecipe
import com.example.recipes.ui.models.toRecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeInformationViewModel : ViewModel() {
    val currentRecipe = MutableLiveData<Recipe?>()

    fun getRecipe(id: Long?, db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipe = db.recipeDao().getRecipeById(id ?: -1)
                currentRecipe.postValue(recipe?.toRecipe())
            }
        }
    }

    fun deleteRecipe(db: AppDatabase, completeAction: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                db.recipeDao().delete(currentRecipe.value?.toRecipeEntity())
            }
        }.invokeOnCompletion {
            completeAction()
        }
    }
}