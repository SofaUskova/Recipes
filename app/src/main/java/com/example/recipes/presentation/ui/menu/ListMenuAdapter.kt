package com.example.recipes.presentation.ui.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemMenuBinding
import com.example.recipes.presentation.models.Menu
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.ui.common.loadImage

fun interface CardClickListener {
    fun onClick(listOfRecipe: List<Recipe>)
}

class ListMenuAdapter (
    private val cardClickListener: CardClickListener,
) : RecyclerView.Adapter<ListMenuAdapter.MenuViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
            return oldItem == newItem
        }
    }

    private val asyncListDiffer = AsyncListDiffer(this, diffUtil)

    fun setData(data: List<Menu>?) {
        asyncListDiffer.submitList(data)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuBinding.inflate(inflater, parent, false)

        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = asyncListDiffer.currentList[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvMenuTitle.text = menu.title
            loadImage(context, menu.photo, imMenuPicture)
            cvMenu.setOnClickListener { cardClickListener.onClick(menu.listOfRecipe) }
        }
    }

    class MenuViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)

}