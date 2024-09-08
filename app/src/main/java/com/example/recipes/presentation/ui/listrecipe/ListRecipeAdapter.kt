package com.example.recipes.presentation.ui.listrecipe

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemMinRecipeBinding
import com.example.recipes.presentation.models.CheckableRecipe
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.ui.common.loadImage
import com.example.recipes.presentation.ui.common.visibility

fun interface CardClickListener {
    fun onClick(recipe: Recipe)
}

class ListRecipeAdapter(
    private val isChecked: Boolean = false,
    private val cardClickListener: CardClickListener,
) : RecyclerView.Adapter<ListRecipeAdapter.RecipeViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setData(data: List<Recipe>?) {
        asyncListDiffer.submitList(data)
    }

    fun getListCheckedRecipe(): List<CheckableRecipe> {
        return asyncListDiffer.currentList
            .filterIsInstance<CheckableRecipe>()
            .filter { it.isChecked }
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMinRecipeBinding.inflate(inflater, parent, false)

        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = asyncListDiffer.currentList[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvName.text = recipe.name
            tvTime.text = recipe.formattedTime
            tvIngredients.text = recipe.ingredientsByComma

            cbChoice.visibility(isChecked)
            cbChoice.setOnClickListener {
                (recipe as? CheckableRecipe)?.isChecked = cbChoice.isChecked
            }

            cvRecipe.setOnClickListener {
                if (isChecked) {
                    val isChecked = (recipe as? CheckableRecipe)?.isChecked ?: false
                    cbChoice.isChecked = !isChecked
                    (recipe as? CheckableRecipe)?.isChecked = !isChecked
                }
                cardClickListener.onClick(recipe)
            }

            loadImage(context, recipe.photo, imRecipePicture)
        }
    }

    class RecipeViewHolder(val binding: ItemMinRecipeBinding) : RecyclerView.ViewHolder(binding.root)

}

fun ListRecipeAdapter.setAdapter(
    recyclerView: RecyclerView,
    type: LayoutType,
    context: Context
) {
    recyclerView.layoutManager =
        if (type == LayoutType.LINEAR) {
            LinearLayoutManager(context)
        } else {
            GridLayoutManager(context, 2)
        }
    recyclerView.adapter = this
}

enum class LayoutType { LINEAR, GRID }
