package com.example.recipes.di.common.modules

import com.example.recipes.presentation.ui.listrecipe.ListRecipeBottomSheetFragment
import com.example.recipes.presentation.ui.listrecipe.ListRecipeFragment
import com.example.recipes.presentation.ui.menu.MenuFragment
import com.example.recipes.presentation.ui.newrecipe.NewRecipeFragment
import com.example.recipes.presentation.ui.recipe.RecipeInformationFragment
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

    @ContributesAndroidInjector
    abstract fun bindMenuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun bindListRecipeBottomSheet(): ListRecipeBottomSheetFragment
}
