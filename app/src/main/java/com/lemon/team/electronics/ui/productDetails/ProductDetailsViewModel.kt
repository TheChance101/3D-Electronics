package com.lemon.team.electronics.ui.productDetails

import androidx.lifecycle.*
import com.lemon.team.electronics.model.repository.Repository
import com.lemon.team.electronics.model.response.Product
import com.lemon.team.electronics.ui.base.BaseViewModel
import com.lemon.team.electronics.util.*
import kotlinx.coroutines.launch

class ProductDetailsViewModel : BaseViewModel(),ImageInteractionListener {

    private var _detailsProduct = MutableLiveData<State<Product?>>()
    val detailsProduct :LiveData<State<Product?>> =_detailsProduct

    private var _onclickBack =MutableLiveData<Event<Boolean>>()
    val onclickBack :LiveData<Event<Boolean>> = _onclickBack

    private var _onclickWish =MutableLiveData<Event<String>>()
    val onclickWish :LiveData<Event<String>> = _onclickWish

    private var _onclickAdd =MutableLiveData<Event<String>>()
    val onclickAdd :LiveData<Event<String>> = _onclickAdd

    private var _onClickMainImage =MutableLiveData<Event<String>>()
    val onclickMainImage :LiveData<Event<String>> = _onClickMainImage

    private var _clickSharedProduct = MutableLiveData<Event<String>>()
    val clickSharedProduct: LiveData<Event<String>> = _clickSharedProduct

    var piecesNumber = MutableLiveData(1)


    val images =Transformations.map(detailsProduct){ state ->
        state.toData()?.images?.map { imageResponse ->
            imageResponse.productImageLocation
        }
    }

    val mainImage= MediatorLiveData<String>().apply {
        addSource(detailsProduct){ state ->
            if(state is State.Success){
                state.data?.mainImage?.let { url ->
                    updateMainImage(url)
                }
            }
        }
    }

    private fun updateMainImage(url:String){
        mainImage.postValue(url)
    }


    override fun onclickImage(url: String) {
        mainImage.postValue(url)
    }



    fun getDetailsProduct(productId: String) {
        collectResponse(Repository.getProductById(productId)) { state ->
            _detailsProduct.postValue(state)
        }
    }

    fun onclickBack(){
        _onclickBack.postValue(Event(true))
    }

    fun onclickWish(productId: String){
        _onclickWish.postValue(Event(productId))
    }

    fun onclickAddToCart(){
        addOrUpdateItem(detailsProduct.value?.toData())
    }


    private fun addOrUpdateItem(item: Product?) {
        viewModelScope.launch {
            if (!isItemExists()!!){
                setItem(item)?.let { Repository.insertProduct(it) }
                _onclickAdd.postValue(Event("1"))
            }
            else {
                updateItem(item)
                _onclickAdd.postValue(Event(piecesNumber.value.toString()))
            }
        }
    }

    fun setItem(product: Product?) =
        piecesNumber.value?.let { product?.toItemEntity(it) }

    private suspend fun isItemExists() =
        detailsProduct.value?.toData()?.let { Repository.checkItemExists(it.id) }


    private suspend fun  updateItem(product: Product?) {
        product?.price.let {
            Repository.updateCartItem(
                product!!.id,
                piecesNumber.value!!,
                it!!.times(piecesNumber.value!!)
            )
        }
    }



    fun onclickShare(productId: String){
        _clickSharedProduct.postValue(Event(productId))
    }

    fun onClickMainImage(){
        mainImage.value?.let { url ->
            _onClickMainImage.postValue(Event(url))
        }
    }

}
