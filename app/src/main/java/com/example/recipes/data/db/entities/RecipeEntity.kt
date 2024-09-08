package com.example.recipes.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time")
    val time: Float,
    @ColumnInfo(name = "recipe")
    val recipe: String,
    @ColumnInfo(name = "menu_title", defaultValue = " ")
    val menuTitle: String,
)