package com.lemon.team.electronics.ui.checkout

import android.view.*
import com.lemon.team.electronics.R
import com.lemon.team.electronics.databinding.FragmentCheckoutBinding
import com.lemon.team.electronics.ui.base.BaseFragment

class CheckoutFragment:BaseFragment<FragmentCheckoutBinding,CheckoutViewModel>() {

    override val useActivityViewModel = false

    override val layoutId: Int = R.layout.fragment_checkout
    override val viewModelClass = CheckoutViewModel::class.java

    override fun observeEvents() {

    }

    override fun setUpBinding() {
        binding.apply {
            checkoutRecycler.adapter =
                PurchasedProductsRecyclerAdapter(mutableListOf(), this@CheckoutFragment.viewModel)
        }
    }

}