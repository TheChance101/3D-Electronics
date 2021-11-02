package com.lemon.team.electronics.ui.home

import com.lemon.team.electronics.model.response.categories.CategoriesResponseItem
import com.lemon.team.electronics.model.response.productById.ProductResponse
import com.lemon.team.electronics.model.response.productsByCategoryId.Content


sealed class HomeItem(val rank: Int) {
    class SlideType(val items: List<String>) : HomeItem(0)
    class SearchType : HomeItem(1)
    class CategoriesType(val items: List<CategoriesResponseItem>, val title: String) : HomeItem(2)
    class BestProductType(val items: List<ProductResponse>, val title: String) : HomeItem(3)
    class ElementCategoriesType(val items: List<Content>, val title: String) : HomeItem(4)
}