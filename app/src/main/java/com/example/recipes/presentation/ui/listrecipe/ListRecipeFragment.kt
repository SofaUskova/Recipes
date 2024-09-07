package com.example.recipes.presentation.ui.listrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentListRecipeBinding
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.ui.recipe.RecipeInformationFragment.Companion.RECIPE_ID
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListRecipeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentListRecipeBinding
    private lateinit var viewModel: ListRecipeViewModel
    private lateinit var adapter: ListRecipeAdapter
    private lateinit var cardClickListener: CardClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory)[ListRecipeViewModel::class.java]
        binding = FragmentListRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAddNewRecipeBtn(view)
        configAdapter(view)
        configViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllRecipes()
    }

    private fun configViewModel() {
        viewModel.listOfRecipes.observe(viewLifecycleOwner) {
            updateRecyclerData(it)
        }
    }

    private fun configAddNewRecipeBtn(view: View) {
        binding.btnAddNewRecipe.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_navigation_list_recipes_to_navigation_new_recipe)
        }
    }

    private fun configAdapter(view: View) {
        configCardClickListener(view)
        adapter = ListRecipeAdapter(cardClickListener)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }

    private fun configCardClickListener(view: View) {
        cardClickListener = CardClickListener { recipe ->
            Navigation.findNavController(view).navigate(
                R.id.action_navigation_list_recipes_to_navigation_recipe_info,
                bundleOf(RECIPE_ID to recipe.id)
            )
        }
    }

    private fun updateRecyclerData(listOfRecipes: List<Recipe>?) {
        adapter.setData(listOfRecipes)
    }

}