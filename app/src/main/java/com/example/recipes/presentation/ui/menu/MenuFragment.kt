package com.example.recipes.presentation.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.R
import com.example.recipes.databinding.FragmentMenuBinding
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentMenuBinding
    private lateinit var viewModel: MenuViewModel
    private lateinit var adapter: ListMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListOfMenu()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configAdapter()
        configViewModel()
        configBackStackListener(view)

        binding.btnCreateMenu.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_menu_to_bottom_sheet_list_recipe)
        }
    }

    private fun configBackStackListener(view: View) {
        lifecycleScope.launch {
            Navigation.findNavController(view).currentBackStackEntryFlow
                .distinctUntilChanged()
                .collect { backStackEntry ->
                    val isRefresh = backStackEntry.savedStateHandle.getStateFlow(REFRESH, false)
                    if (isRefresh.value) viewModel.getListOfMenu()
                }
        }
    }

    private fun configViewModel() {
        viewModel.listOfMenu.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun configAdapter() {
        adapter = ListMenuAdapter { recipes ->
            //todo go to recipes list
        }
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.adapter = adapter
    }

    companion object {
        const val REFRESH = "need_to_refresh"
    }

}