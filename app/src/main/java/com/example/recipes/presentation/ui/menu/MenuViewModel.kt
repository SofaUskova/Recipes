package com.example.recipes.presentation.ui.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.presentation.models.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val interactor: RecipeInteractor,
) : ViewModel() {
    var listOfMenu = MutableLiveData<List<Menu>?>()

    fun getListOfMenu() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val menu = interactor.getListMenu()
                listOfMenu.postValue(menu)
            }
        }
    }

}