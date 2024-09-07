package com.example.recipes.presentation.ui.listrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.presentation.models.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListRecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor,
) : ViewModel() {
    var listOfRecipes = MutableLiveData<List<Recipe>?>()

    fun getAllRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipes = interactor.getListRecipe()
                listOfRecipes.postValue(recipes)
            }
        }
    }

}