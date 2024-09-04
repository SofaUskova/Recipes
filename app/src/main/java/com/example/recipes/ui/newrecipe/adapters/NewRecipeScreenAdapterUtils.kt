package com.example.recipes.ui.newrecipe.adapters

import android.widget.AutoCompleteTextView
import com.example.recipes.ui.models.RecipeItem
import com.example.recipes.ui.models.RecipeItemType
import com.google.android.material.textfield.TextInputEditText

inline fun <reified T> List<RecipeItem>.findByClass(): T? {
    return find { it is T } as? T
}

inline fun <reified T : RecipeItem> List<RecipeItem>.filterByType(type: RecipeItemType): T? {
    return filterIsInstance<T>().find { it.type == type }
}

fun TextInputEditText.isChangedByUser(): Boolean = hasFocus()

fun AutoCompleteTextView.isChangedByUser(): Boolean = hasFocus()