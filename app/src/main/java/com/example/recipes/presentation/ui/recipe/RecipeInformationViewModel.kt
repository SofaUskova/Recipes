package com.example.recipes.presentation.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.presentation.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeInformationViewModel @Inject constructor(
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

    fun deleteRecipe(completeAction: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                interactor.deleteRecipe(currentRecipe.value)
            }
        }.invokeOnCompletion {
            completeAction()
        }
    }
}