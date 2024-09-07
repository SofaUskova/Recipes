package com.example.recipes.data.repositories

import com.example.recipes.data.db.dao.IngredientDao
import com.example.recipes.data.db.entities.IngredientEntity
import com.example.recipes.domain.repositories.IIngredientRepository
import javax.inject.Inject

class IngredientRepository @Inject constructor(
    private val dao: IngredientDao
) : IIngredientRepository {

    override suspend fun insertOrUpdateIngredient(ingredient: IngredientEntity) {
        dao.insertOrUpdateIngredient(ingredient)
    }
}