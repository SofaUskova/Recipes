package com.example.recipes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private const val TAG = "recipes.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): AppDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, TAG).build()
        }
    }
}