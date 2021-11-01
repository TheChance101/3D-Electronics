package com.lemon.team.electronics.ui.category

import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCategoryBinding
import com.lemon.team.electronics.ui.base.BaseFragment
import com.lemon.team.electronics.ui.categories.CategoriesFragmentDirections
import com.lemon.team.electronics.ui.search.SearchFragmentDirections
import com.lemon.team.electronics.util.EventObserver
import com.lemon.team.electronics.util.goToFragment

class CategoryFragment:BaseFragment<FragmentCategoryBinding, CategoryViewModel>() {

    override val layoutId: Int= R.layout.fragment_category
    override val viewModel: CategoryViewModel by viewModels()
    val args: CategoryFragmentArgs by navArgs()

    override val bindingInflater: (LayoutInflater, Int, ViewGroup?, Boolean) -> FragmentCategoryBinding
        =DataBindingUtil::inflate

    override fun setUp() {
        binding.apply {
            lifecycleOwner= viewLifecycleOwner
            viewModel= this@CategoryFragment.viewModel
            categoryName = args.categoryName
            categoryRecycler.adapter =
                CategoryAdapter(mutableListOf(), this@CategoryFragment.viewModel)
        }

        viewModel.getProductsByCategoryId(args.categoryId)
        observeEvent()
    }

    private fun observeEvent(){

        viewModel.clickItemEvent.observe(this, EventObserver{
            view?.goToFragment(CategoryFragmentDirections.actionCategoryFragmentToProductFragment(it))
        })
        viewModel.clickBackEvent.observe(this, EventObserver{
            findNavController().popBackStack()
        })

    }

}