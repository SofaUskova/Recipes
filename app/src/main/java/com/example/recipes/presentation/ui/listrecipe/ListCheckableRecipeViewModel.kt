package com.example.recipes.presentation.ui.listrecipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.presentation.models.CheckableRecipe
import com.example.recipes.presentation.models.Menu
import com.example.recipes.presentation.models.toCheckableRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListCheckableRecipeViewModel @Inject constructor(
    private val interactor: RecipeInteractor,
) : ViewModel() {
    var listOfRecipes = MutableLiveData<List<CheckableRecipe>?>()

    fun getAllRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val recipes = interactor.getListRecipe()?.map { it.toCheckableRecipe() }
                listOfRecipes.postValue(recipes)
            }
        }
    }

    fun saveMenu(menu: Menu, action: () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                interactor.saveMenu(menu)
            }
        }.invokeOnCompletion { action.invoke() }
    }

}