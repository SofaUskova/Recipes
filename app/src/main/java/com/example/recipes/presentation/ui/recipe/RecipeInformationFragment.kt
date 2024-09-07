package com.example.recipes.presentation.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeInformationBinding
import com.example.recipes.presentation.ui.common.getDrawable
import com.example.recipes.presentation.ui.common.loadImage
import com.example.recipes.presentation.models.Recipe
import com.example.recipes.presentation.ui.newrecipe.NewRecipeFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class RecipeInformationFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentRecipeInformationBinding
    private lateinit var viewModel: RecipeInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory)[RecipeInformationViewModel::class.java]
        binding = FragmentRecipeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configToolbar()
        configViewModel()
    }

    private fun configViewModel() {
        val recipeId = arguments?.getLong(RECIPE_ID)

        viewModel.currentRecipe.observe(viewLifecycleOwner) {
            collectRecipeInfo(it)
        }
        viewModel.getRecipe(recipeId)
    }

    private fun configToolbar() {
        with(binding.toolbar) {
            navigationIcon = getDrawable(requireContext(), R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener { back() }

            inflateMenu(R.menu.edit_and_delete_menu)
            setOnMenuItemClickListener(createToolbarMenuListener())
        }
    }

    private fun createToolbarMenuListener(): Toolbar.OnMenuItemClickListener {
        return Toolbar.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.act_delete -> deleteRecipe()
                R.id.act_edit -> editRecipe()
                else -> Unit
            }
            true
        }
    }

    private fun collectRecipeInfo(recipe: Recipe?) {
        if (recipe == null) return //todo show error

        with(binding) {
            tvName.text = recipe.name
            tvTime.text = recipe.formattedTime
            tvListIngredients.text = recipe.ingredientsByDot
            tvRecipe.text = recipe.recipe

            loadImage(requireContext(), recipe.photo, imPicture)
        }
    }

    private fun back() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun deleteRecipe() {
        viewModel.deleteRecipe { back() }
    }

    private fun editRecipe() {
        val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)
        navController.navigate(
            R.id.action_navigation_recipe_info_to_navigation_new_recipe,
            bundleOf(NewRecipeFragment.RECIPE_ID to viewModel.currentRecipe.value?.id)
        )
    }

    companion object {
        const val RECIPE_ID = "recipe_id"
    }

}