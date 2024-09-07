package com.example.recipes.di.modules

import com.example.recipes.ui.listrecipe.ListRecipeFragment
import com.example.recipes.ui.newrecipe.NewRecipeFragment
import com.example.recipes.ui.recipe.RecipeInformationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindListRecipeFragment(): ListRecipeFragment

    @ContributesAndroidInjector
    abstract fun bindNewRecipeFragment(): NewRecipeFragment

    @ContributesAndroidInjector
    abstract fun bindRecipeInformationFragment(): RecipeInformationFragment
}
