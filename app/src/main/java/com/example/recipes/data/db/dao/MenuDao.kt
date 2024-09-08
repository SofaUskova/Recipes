package com.example.recipes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.recipes.data.db.entities.MenuEntity
import com.example.recipes.data.db.entities.MenuWithRecipes
import com.example.recipes.data.db.entities.RecipeWithIngredientsEntity

@Dao
interface MenuDao {

    @Transaction
    @Insert
    fun insertMenu(menu: MenuEntity)

    @Transaction
    @Query("SELECT * FROM menu")
    fun getAllMenu(): List<MenuWithRecipes>?


    @Transaction
    @Query("SELECT * FROM recipe")
    fun geRecipeWithIngredients(): List<RecipeWithIngredientsEntity>?

}