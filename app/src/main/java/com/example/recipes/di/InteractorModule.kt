package com.example.recipes.di

import com.example.recipes.domain.RecipeInteractor
import com.example.recipes.domain.repositories.IIngredientRepository
import com.example.recipes.domain.repositories.IMenuRepository
import com.example.recipes.domain.repositories.IRecipeRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideRecipeInteractor(
        repositoryRecipe: IRecipeRepository,
        repositoryIngredient: IIngredientRepository,
        repositoryMenu: IMenuRepository
    ): RecipeInteractor {
        return RecipeInteractor(repositoryRecipe, repositoryIngredient, repositoryMenu)
    }

}