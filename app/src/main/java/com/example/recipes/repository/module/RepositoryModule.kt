package com.example.recipes.repository.module

import com.example.recipes.db.dao.IngredientDao
import com.example.recipes.db.dao.RecipeDao
import com.example.recipes.repository.interfaces.IIngredientRepository
import com.example.recipes.repository.interfaces.IRecipeRepository
import com.example.recipes.repository.IngredientRepository
import com.example.recipes.repository.RecipeRepository
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
