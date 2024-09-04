package com.example.recipes.ui.listrecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemMinRecipeBinding
import com.example.recipes.ui.loadImage
import com.example.recipes.ui.models.Recipe

fun interface CardClickListener {
    fun onClick(recipe: Recipe)
}

class ListRecipeAdapter(
    private val cardClickListener: CardClickListener
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

    fun setData(data: List<Recipe>) {
        asyncListDiffer.submitList(data)
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

            cvRecipe.setOnClickListener { cardClickListener.onClick(recipe) }

            loadImage(context, recipe.photo, imRecipePicture)
        }
    }

    class RecipeViewHolder(val binding: ItemMinRecipeBinding) : RecyclerView.ViewHolder(binding.root)

}