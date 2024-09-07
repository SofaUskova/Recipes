package com.example.recipes.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.di.ViewModelFactory
import com.example.recipes.di.ViewModelKey
import com.example.recipes.ui.listrecipe.ListRecipeViewModel
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
}