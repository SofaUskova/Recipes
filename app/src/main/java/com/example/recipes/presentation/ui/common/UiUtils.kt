package com.example.recipes.presentation.ui.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.google.android.material.textfield.TextInputEditText

fun <T> AutoCompleteTextView.configDropDownAdapter(
    context: Context,
    layoutRes: Int,
    listOfValues: List<T>
) {
    val arrayAdapter = ArrayAdapter(context, layoutRes, listOfValues)
    setAdapter(arrayAdapter)
}

fun getDrawable(context: Context, idRes: Int): Drawable? {
    return AppCompatResources.getDrawable(context, idRes)
}

fun loadImage(
    context: Context,
    imageUrl: String?,
    imageView: ImageView,
    errorRes: Int = R.drawable.ic_error_circle_black,
    placeholderRes: Int = R.drawable.ic_add_photo_black
) {
    Glide.with(context)
        .load(imageUrl)
        .error(errorRes)
        .placeholder(placeholderRes)
        .into(imageView)
}

fun setAutoCompleteTextError(isEmpty: Boolean?, autoCompleteTextView: AutoCompleteTextView) {
    if (isEmpty == true) autoCompleteTextView.error = "" else autoCompleteTextView.error = null
}

fun setEditTextError(isEmpty: Boolean?, editText: TextInputEditText) {
    if (isEmpty == true) editText.error = "" else editText.error = null
}