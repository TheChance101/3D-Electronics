package com.lemon.team.electronics.ui.category

import com.lemon.team.electronics.ui.base.BaseInteractionListener

interface CategoryInteractionListener: BaseInteractionListener {

    fun onClickProduct(productId: String)

}