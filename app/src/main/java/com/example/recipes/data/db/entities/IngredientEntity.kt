package com.example.recipes.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.recipes.presentation.models.Metric

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "quantity")
    val quantity: Float,
    @ColumnInfo(name = "metric")
    @TypeConverters(MetricConverter::class)
    val metric: Metric,
    @ColumnInfo(name = "recipe_id")
    val recipeId: Long
)

class MetricConverter {

    @TypeConverter
    fun toMetric(metric: String): Metric {
       return Metric.entries.find { it.abbreviation == metric } ?: Metric.GRAM
    }

    @TypeConverter
    fun fromMetric(metric: Metric): String {
        return metric.abbreviation
    }
}