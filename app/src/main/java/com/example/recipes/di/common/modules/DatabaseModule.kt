package com.example.recipes.di.common.modules

import android.content.Context
import androidx.room.Room
import com.example.recipes.data.db.AppDatabase
import com.example.recipes.data.db.dao.IngredientDao
import com.example.recipes.data.db.dao.MenuDao
import com.example.recipes.data.db.dao.RecipeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideIngredientDao(db: AppDatabase): IngredientDao {
        return db.ingredientDao()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(db: AppDatabase): RecipeDao {
        return db.recipeDao()
    }

    @Provides
    @Singleton
    fun provideMenuDao(db: AppDatabase): MenuDao {
        return db.menuDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    companion object {
        private const val DATABASE_NAME = "recipes.db"
    }

}