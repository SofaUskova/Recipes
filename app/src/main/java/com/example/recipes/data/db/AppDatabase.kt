package com.example.recipes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recipes.data.db.dao.IngredientDao
import com.example.recipes.data.db.dao.MenuDao
import com.example.recipes.data.db.dao.RecipeDao
import com.example.recipes.data.db.entities.IngredientEntity
import com.example.recipes.data.db.entities.MenuEntity
import com.example.recipes.data.db.entities.MetricConverter
import com.example.recipes.data.db.entities.RecipeEntity

@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
        MenuEntity::class,
    ]
)
@TypeConverters(MetricConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun menuDao(): MenuDao

}
