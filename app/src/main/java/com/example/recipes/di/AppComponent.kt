package com.example.recipes.di

import android.app.Application
import com.example.recipes.App
import com.example.recipes.di.modules.ActivityBuilderModule
import com.example.recipes.di.modules.ContextModule
import com.example.recipes.di.modules.FragmentBuilderModule
import com.example.recipes.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        ViewModelModule::class,
        ContextModule::class,
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
