package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.Content
import com.lemon.team.electronics.ui.base.BaseRecyclerAdapter
import com.lemon.team.electronics.ui.home.HomeInteractionListener

class CategoryAdapter(
    items: List<Content>,
    listener: ProductInteractionListener
): BaseRecyclerAdapter<Content>(items, listener) {
    override val layoutId: Int = R.layout.item_product
}