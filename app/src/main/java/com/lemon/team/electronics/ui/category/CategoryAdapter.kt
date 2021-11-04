package com.lemon.team.electronics.ui

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.ui.category.ProductInteractionListener

class CategoryAdapter(
    items: List<Product>,
    listener: ProductInteractionListener
): BaseRecyclerAdapter<Product>(items, listener) {
    override val layoutId: Int = R.layout.item_product
}