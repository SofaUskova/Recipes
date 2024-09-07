package com.example.recipes.di.modules

import android.content.Context
import androidx.room.Room
import com.example.recipes.db.AppDatabase
import com.example.recipes.db.dao.IngredientDao
import com.example.recipes.db.dao.RecipeDao
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