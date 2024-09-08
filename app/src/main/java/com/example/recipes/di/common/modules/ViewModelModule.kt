package com.example.recipes.di.common.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.di.common.ViewModelFactory
import com.example.recipes.di.common.ViewModelKey
import com.example.recipes.presentation.ui.listrecipe.ListCheckableRecipeViewModel
import com.example.recipes.presentation.ui.listrecipe.ListRecipeViewModel
import com.example.recipes.presentation.ui.menu.MenuViewModel
import com.example.recipes.presentation.ui.newrecipe.NewRecipeViewModel
import com.example.recipes.presentation.ui.recipe.RecipeInformationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListRecipeViewModel::class)
    abstract fun bindListRecipeViewModel(viewModel: ListRecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewRecipeViewModel::class)
    abstract fun bindNewRecipeViewModel(viewModel: NewRecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipeInformationViewModel::class)
    abstract fun bindRecipeInformationViewModel(viewModel: RecipeInformationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(viewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListCheckableRecipeViewModel::class)
    abstract fun bindListCheckableRecipeViewModel(viewModel: ListCheckableRecipeViewModel): ViewModel
}