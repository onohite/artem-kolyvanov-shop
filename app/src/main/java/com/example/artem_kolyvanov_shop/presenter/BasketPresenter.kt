package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.model.MainApi
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.domain.model.ProductItem.Companion.createProductItem
import com.example.artem_kolyvanov_shop.domain.model.Value
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import java.io.IOException
import java.net.UnknownHostException as UnknownHostException

@InjectViewState
class BasketPresenter(
    private val mainApi: MainApi
) : BasePresenter<ProductsView>() {
    private var products = mutableListOf<ProductItem>()

    private val product = createProductItem("6", "MacBook", "123321", 1200.0, 0)

    private fun setData(){
        viewState.setProducts(products)
    }

    fun removeItem(product: ProductItem){
        val position = products.indexOf(product)
        products.removeAt(position)
        viewState.removeProduct(position)
    }

    fun addData(){
        viewState.addProduct(product)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestLaunch()

    }

    private suspend fun makeRequest(){
        val remoteProducts = mainApi.allProducts()
        val productItems = remoteProducts.map { remoteProduct ->
            val productItem = createProductItem(
                remoteProduct.id,
                remoteProduct.name,
                remoteProduct.imageUrl,
                remoteProduct.price,
                remoteProduct.discountPercent)
            productItem
        }.toMutableList()
        products = productItems
    }

    fun requestLaunch () {
        launch {
            try {
                makeRequest()
                setData()
            }
            catch(e: UnknownHostException){
                alertError("Ошибка подключения сети")
            }
        }
    }

    private fun alertError(msg:String){
        viewState.showException(msg)
    }

    fun onProductClick(product: ProductItem) {
        viewState.showProductDerailed(product)
    }

    override fun attachView(view: ProductsView?) {
        super.attachView(view)
        setData()
    }
}