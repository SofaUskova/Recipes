package com.example.recipes.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.repository.RecipeRepository
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.toRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeInformationViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    val currentRecipe = MutableLiveData<Recipe?>()

    fun getRecipe(id: Long?) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipe = repository.getRecipeById(id ?: -1)
                currentRecipe.postValue(recipe?.toRecipe())
            }
        }
    }

    fun deleteRecipe(completeAction: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                repository.delete(currentRecipe.value)
            }
        }.invokeOnCompletion {
            completeAction()
        }
    }
}