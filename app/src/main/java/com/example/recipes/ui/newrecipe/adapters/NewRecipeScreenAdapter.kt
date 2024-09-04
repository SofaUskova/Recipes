package com.example.recipes.ui.newrecipe.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.databinding.ItemAutoCompliteEditTextBinding
import com.example.recipes.databinding.ItemButtonBinding
import com.example.recipes.databinding.ItemEditTextBinding
import com.example.recipes.databinding.ItemIngredientBinding
import com.example.recipes.databinding.ItemPictureBinding
import com.example.recipes.ui.configDropDownAdapter
import com.example.recipes.ui.loadImage
import com.example.recipes.ui.models.Metric
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.models.RecipeButtonItem
import com.example.recipes.ui.models.RecipeImageItem
import com.example.recipes.ui.models.RecipeIngredientItem
import com.example.recipes.ui.models.RecipeItem
import com.example.recipes.ui.models.RecipeItemType
import com.example.recipes.ui.models.RecipeTextItem
import com.example.recipes.ui.models.abbreviationOf
import com.example.recipes.ui.models.toIngredient
import com.example.recipes.ui.quantityToFloat
import com.example.recipes.ui.setAutoCompleteTextError
import com.example.recipes.ui.setEditTextError
import com.example.recipes.ui.timeToFloat
import kotlin.random.Random


class NewRecipeScreenAdapter(
    private val recipeId: Long?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<RecipeItem>() {
        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setData(newList: List<RecipeItem>) {
        asyncListDiffer.submitList(newList)
    }

    //todo rewrite this
    fun setImage(uri: Uri) {
        val newList = asyncListDiffer.currentList
            .toMutableList()
            .apply {
                replaceAll { item ->
                    when (item) {
                        is RecipeImageItem -> {
                            val newItem = item.copy(imageUrl = uri.toString())
                            newItem
                        }
                        else -> {
                            item
                        }
                    }
                }
            }
        setData(newList)
    }

    fun addEmptyIngredient() {
        val newAsyncListDiffer = asyncListDiffer.currentList.toMutableList().apply {
            val indexOfLastIngredient = indexOf(findLast { it is RecipeIngredientItem })
            add(index = indexOfLastIngredient + 1, element = RecipeIngredientItem())
        }
        setData(newAsyncListDiffer)
    }

    fun hasEmptyFields(): Boolean {
        return asyncListDiffer.currentList.find { item ->
            (item as? RecipeIngredientItem)?.recheckValues() == true
                    || (item as? RecipeTextItem)?.recheckValues() == true
        } != null
    }

    fun updateFields() {
        val newList = createListOfRecheckedEmptyItems()
        setData(newList)
    }

    //todo rewrite this
    private fun createListOfRecheckedEmptyItems(): List<RecipeItem> {
        return asyncListDiffer.currentList
            .toMutableList()
            .apply {
                replaceAll { item ->
                    when (item) {
                        is RecipeIngredientItem -> {
                            val newItem = item.copy(isEmpty = item.recheckValues())
                            newItem
                        }
                        is RecipeTextItem -> {
                            val newItem = item.copy(isEmpty = item.recheckValues())
                            newItem
                        }
                        else -> {
                            item
                        }
                    }
                }
            }
    }

    fun collectNewRecipe(): Recipe {
        val list = asyncListDiffer.currentList
        val time = list.filterByType<RecipeTextItem>(RecipeItemType.TIME)?.text ?: ""
        val ingredients = list.filterIsInstance<RecipeIngredientItem>()

        return Recipe(
            id = recipeId ?: Random.nextLong(),
            photo = list.findByClass<RecipeImageItem>()?.imageUrl ?: "",
            name = list.filterByType<RecipeTextItem>(RecipeItemType.NAME)?.text ?: "",
            time = time.timeToFloat(),
            ingredients = ingredients.map { it.toIngredient() },
            recipe = list.filterByType<RecipeTextItem>(RecipeItemType.RECIPE)?.text ?: "",
        )
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun getItemViewType(position: Int): Int {
        return asyncListDiffer.currentList[position].type.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            RecipeItemType.IMAGE.type -> {
                val binding = ItemPictureBinding.inflate(inflater, parent, false)
                PictureViewHolder(binding)
            }
            RecipeItemType.BUTTON.type -> {
                val binding = ItemButtonBinding.inflate(inflater, parent, false)
                ButtonViewHolder(binding)
            }
            RecipeItemType.TIME.type -> {
                val binding = ItemAutoCompliteEditTextBinding.inflate(inflater, parent, false)
                AutoCompleteEditTextViewHolder(binding)
            }
            RecipeItemType.INGREDIENT.type -> {
                val binding = ItemIngredientBinding.inflate(inflater, parent, false)
                IngredientViewHolder(binding)
            }
            else -> {
                val binding = ItemEditTextBinding.inflate(inflater, parent, false)
                EditTextViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = asyncListDiffer.currentList[position]

        when (holder) {
            is IngredientViewHolder -> holder.bind(item as? RecipeIngredientItem)
            is AutoCompleteEditTextViewHolder -> holder.bind(item as? RecipeTextItem)
            is EditTextViewHolder -> holder.bind(item as? RecipeTextItem)
            is ButtonViewHolder -> holder.bind(item as? RecipeButtonItem)
            is PictureViewHolder -> holder.bind(item as? RecipeImageItem)
        }
    }

    private inner class IngredientViewHolder(
        private val binding: ItemIngredientBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeIngredientItem?) {
            configIngredientItem(binding, item)

            with(binding) {
                etIngredient.doAfterTextChanged { text ->
                    if (etIngredient.isChangedByUser()) {
                        item?.text = text.toString()
                    }
                }
                etQuantity.doAfterTextChanged { quantity ->
                    if (etQuantity.isChangedByUser()) {
                        item?.quantity = quantity?.toString()?.quantityToFloat() ?: 0f
                    }
                }
                actvMetric.doAfterTextChanged { metric ->
                    if (actvMetric.isChangedByUser()) {
                        item?.metric = abbreviationOf(metric.toString())
                    }
                }
                ivRemoveIngredient.setOnClickListener {
                    val newList = asyncListDiffer.currentList.toMutableList().apply { remove(item) }
                    setData(newList)
                }
            }
        }

        private fun configIngredientItem(
            binding: ItemIngredientBinding,
            item: RecipeIngredientItem?
        ) {
            with(binding) {
                val context = root.context

                etIngredient.setText(item?.text ?: "")
                etQuantity.setText(item?.quantity?.toString() ?: "")

                actvMetric.configDropDownAdapter(context, R.layout.item_dropdown, listOfMetric)
                actvMetric.setText(item?.metric?.abbreviation ?: Metric.GRAM.abbreviation)

                setEditTextError(item?.isEmpty, etIngredient)
                setEditTextError(item?.isEmpty, etQuantity)
                setAutoCompleteTextError(item?.isEmpty, actvMetric)
            }
        }
    }

    private inner class AutoCompleteEditTextViewHolder(
        private val binding: ItemAutoCompliteEditTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeTextItem?) {

            with(binding) {
                val context = root.context

                autoCompleteTilItem.hint = item?.hint ?: ""

                actvItem.configDropDownAdapter(context, R.layout.item_dropdown, listOfTimes)
                actvItem.setText(item?.text ?: "")
                actvItem.doAfterTextChanged { item?.text = it.toString() }
                setAutoCompleteTextError(item?.isEmpty, binding.actvItem)
            }
        }
    }

    private inner class EditTextViewHolder(
        private val binding: ItemEditTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeTextItem?) {

            with(binding) {
                tilItem.hint = item?.hint ?: ""
                etItem.setText(item?.text ?: "")

                etItem.doAfterTextChanged { item?.text = it.toString() }
                setEditTextError(item?.isEmpty, etItem)
            }
        }
    }

    private inner class ButtonViewHolder(
        private val binding: ItemButtonBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeButtonItem?) {

            with(binding) {
                btnItem.text = item?.text ?: ""
                btnItem.setOnClickListener { item?.action?.invoke() }
            }
        }
    }

    private inner class PictureViewHolder(
        private val binding: ItemPictureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeImageItem?) {
            val context = binding.root.context

            with(binding) {
                loadImage(context, item?.imageUrl, imPictureItem)
                imPictureItem.setOnClickListener { item?.action?.invoke() }
            }
        }
    }

    companion object {
        private val listOfTimes = listOf("0.5h", "1h", "1.5h", "2h", "2.5h", "3h", "3.5h")

        private val listOfMetric = listOf(
            Metric.GRAM.abbreviation,
            Metric.MILLILITER.abbreviation,
            Metric.TABLESPOON.abbreviation,
            Metric.TEASPOON.abbreviation
        )
    }

}