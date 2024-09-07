package com.example.recipes.di

import com.example.recipes.data.db.dao.IngredientDao
import com.example.recipes.data.db.dao.RecipeDao
import com.example.recipes.domain.repositories.IIngredientRepository
import com.example.recipes.domain.repositories.IRecipeRepository
import com.example.recipes.data.repositories.IngredientRepository
import com.example.recipes.data.repositories.RecipeRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideIngredientRepository(dao: IngredientDao): IIngredientRepository {
        return IngredientRepository(dao)
    }

    @Provides
    fun provideRecipeRepository(dao: RecipeDao): IRecipeRepository {
        return RecipeRepository(dao)
    }

}
