package com.example.recipes.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class MenuEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "photo")
    val photo: String
)
