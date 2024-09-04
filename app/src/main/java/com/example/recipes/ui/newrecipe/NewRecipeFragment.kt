package com.example.recipes.ui.newrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentNewRecipeBinding
import com.example.recipes.db.AppDatabase
import com.example.recipes.ui.getDrawable
import com.example.recipes.ui.models.Recipe
import com.example.recipes.ui.newrecipe.adapters.NewRecipeScreenAdapter

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
    private lateinit var viewModel: NewRecipeViewModel
    private lateinit var adapter: NewRecipeScreenAdapter
    private val launcher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.apply { adapter.setImage(this) }
    }
    private lateinit var db: AppDatabase // todo move to di

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[NewRecipeViewModel::class.java]
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        db = AppDatabase(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configToolbar()
        configViewModel()
    }

    private fun configViewModel() {
        viewModel.currentRecipe.observe(viewLifecycleOwner) {
            configAdapter(it)
        }
        viewModel.getRecipe(id = arguments?.getLong(RECIPE_ID), db)
    }

    private fun configToolbar() {
        with(binding.toolbar) {
            navigationIcon = getDrawable(requireContext(), R.drawable.ic_arrow_back_black)
            setNavigationOnClickListener { back() }
        }
    }

    private fun configAdapter(editRecipe: Recipe?) {
        adapter = NewRecipeScreenAdapter(editRecipe?.id)
        binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIngredients.adapter = adapter

        val newList = viewModel.collectNewOrEditRecipeItemsScreen(
            editRecipe = editRecipe,
            addNewEmptyIngredientAction = { adapter.addEmptyIngredient() },
            saveAction = {
                if (adapter.hasEmptyFields()) {
                    adapter.updateFields()
                } else {
                    val newRecipe = adapter.collectNewRecipe()
                    viewModel.saveRecipe(newRecipe, db) { back() }
                }
            },
            pickImagesAction = {
                launcher.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }
        )

        adapter.setData(newList)
    }

    private fun back() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    companion object {
        const val RECIPE_ID = "recipe_id"
    }

}