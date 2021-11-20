package com.lemon.team.electronics.ui.customerInformation

import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonElement
import com.lemon.team.electronics.model.Repository
import com.lemon.team.electronics.model.data.CartItem
import com.lemon.team.electronics.model.order.OrderRequest
import com.lemon.team.electronics.model.order.OrderedProduct
import com.lemon.team.electronics.model.orderResponse.OrderResponse
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.DataClassParser
import com.lemon.team.electronics.util.Event
import com.lemon.team.electronics.util.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CustomerInformationViewModel : BaseViewModel() {

    private val _orderResponse = MutableLiveData<Event<State<OrderResponse?>>>()
    val orderResponse : LiveData<Event<State<OrderResponse?>>> = _orderResponse

    val fullName = MutableLiveData<String>()
    val companyName = MutableLiveData<String>()
    val regionName = MutableLiveData<String>()
    val nearestPoint = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val notes = MutableLiveData<String>()
    val governorate = MutableLiveData<String>()

    fun onSubmitClicked() {
        val order = initOrder()
        val parsedOrder = parseOrder(order)
        makeOrder(parsedOrder)
    }

    private fun parseOrder(order: OrderRequest): JsonElement {
        return DataClassParser.parseToJson(order)
    }

    private fun initOrder(): OrderRequest {
        return OrderRequest(
            name = fullName.value ?: "",
            companyName = companyName.value ?: "",
            regionName = regionName.value ?: "",
            nearestPoint = nearestPoint.value ?: "",
            email = email.value ?: "",
            mobileNumber = phoneNumber.value ?: "",
            notes = notes.value ?: "",
            governorate = governorate.value,
            orderedProducts = Repository.getOrderedProducts()
        )
    }

    private fun makeOrder(order: JsonElement) {
        collectResponse(
            Repository.makeOrder(order)
        ) { orderResponse ->
            _orderResponse.postValue(Event(orderResponse))
            viewModelScope.launch {
                onOrderSuccess()
            }
        }
    }

    private suspend fun onOrderSuccess(){
        Repository.clearCart()
    }

}