package com.example.recipes.di.common.modules

import com.example.recipes.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}
