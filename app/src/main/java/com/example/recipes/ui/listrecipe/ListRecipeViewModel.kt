package com.example.recipes.ui.listrecipe

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

class ListRecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    var listOfRecipes = MutableLiveData<List<Recipe>>()

    fun getAllRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipes = repository.geRecipeWithIngredients()
                listOfRecipes.postValue(recipes?.map { it.toRecipe() })
            }
        }
    }

}