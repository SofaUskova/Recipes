package com.example.recipes.presentation.ui.listrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.R
import com.example.recipes.databinding.BottomSheetListRecipeBinding
import com.example.recipes.presentation.models.Menu
import com.example.recipes.presentation.ui.common.FullScreenBottomSheetDialogFragment
import com.example.recipes.presentation.ui.common.back
import com.example.recipes.presentation.ui.common.isNotEmptyOrBlank
import com.example.recipes.presentation.ui.common.isRefreshParentFragment
import com.example.recipes.presentation.ui.common.showToast
import javax.inject.Inject

class ListRecipeBottomSheetFragment : FullScreenBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ListCheckableRecipeViewModel
    private lateinit var binding: BottomSheetListRecipeBinding
    private lateinit var adapter: ListRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory)[ListCheckableRecipeViewModel::class.java]
        binding = BottomSheetListRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
        configListeners()
        configAdapter()
    }

    private fun configListeners() {
        binding.imClose.setOnClickListener {
            back()
        }
        binding.imAccept.setOnClickListener {
            val listOfRecipes = adapter.getListCheckedRecipe()
            val isTitleFilled = isTitleFilled()
            val isListOfRecipeEmpty = listOfRecipes.isEmpty()

            checkIsFieldFulled(isTitleFilled, isListOfRecipeEmpty)

            if (isTitleFilled && !isListOfRecipeEmpty) {
                val title = binding.etTitle.text.toString()
                val photo = listOfRecipes.first().photo
                val menu = Menu(title, photo, listOfRecipes)

                viewModel.saveMenu(menu) {
                    isRefreshParentFragment(true)
                    back()
                }
            }
        }
    }

    private fun checkIsFieldFulled(isTitleFilled: Boolean, isListOfRecipeEmpty: Boolean) {
        if (!isTitleFilled) {
            showTitleNotFilledError()
        }
        if (isListOfRecipeEmpty) {
            showToast(requireContext(), getString(R.string.collect_menu_chose_recipes))
        }
    }

    private fun showTitleNotFilledError() {
        binding.etTitle.error = ""
    }

    private fun isTitleFilled(): Boolean {
        return binding.etTitle.text.toString().isNotEmptyOrBlank()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllRecipes()
    }

    private fun configAdapter() {
        adapter = ListRecipeAdapter(true) {}
        adapter.setAdapter(binding.recycler, LayoutType.GRID, requireContext())
    }

    private fun configViewModel() {
        viewModel.listOfRecipes.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}