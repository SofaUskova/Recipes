package com.example.recipes.di.common

import android.app.Application
import com.example.recipes.App
import com.example.recipes.di.common.modules.ActivityBuilderModule
import com.example.recipes.di.common.modules.ContextModule
import com.example.recipes.di.common.modules.DatabaseModule
import com.example.recipes.di.common.modules.FragmentBuilderModule
import com.example.recipes.di.common.modules.ViewModelModule
import com.example.recipes.di.RepositoryModule
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
        DatabaseModule::class,
        RepositoryModule::class,
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
