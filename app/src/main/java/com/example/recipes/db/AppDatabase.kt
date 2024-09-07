package com.example.recipes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipes.db.dao.IngredientDao
import com.example.recipes.db.dao.RecipeDao
import com.example.recipes.db.entities.IngredientEntity
import com.example.recipes.db.entities.MetricConverter
import com.example.recipes.db.entities.RecipeEntity

@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
    ]
)
@TypeConverters(MetricConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao

}
