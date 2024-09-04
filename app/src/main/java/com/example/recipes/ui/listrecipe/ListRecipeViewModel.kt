package com.example.recipes.ui.listrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.db.AppDatabase
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.toRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListRecipeViewModel : ViewModel() {
    var listOfRecipes = MutableLiveData<List<Recipe>>()

    fun getAllRecipes(db: AppDatabase) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                
                val recipes = db.recipeDao().geRecipeWithIngredients()
                listOfRecipes.postValue(recipes.map { it.toRecipe() })
            }
        }
    }

}