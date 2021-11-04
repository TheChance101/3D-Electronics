package com.lemon.team.electronics.ui.categories

import com.lemon.team.electronics.R
import com.lemon.team.electronics.model.response.categories.CategoryResponse
import com.lemon.team.electronics.ui.base.*
import com.lemon.team.electronics.ui.home.HomeInteractionListener

class CategoriesAdapter(
    items: List<CategoryResponse>,
    listener: CategoryInteractionListener
) : BaseRecyclerAdapter<CategoryResponse>(items, listener) {

    override val layoutId: Int = R.layout.item_category

}
