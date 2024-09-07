package com.example.recipes.di.modules

import com.example.recipes.ui.listrecipe.ListRecipeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun binListRecipeFragment(): ListRecipeFragment
}
