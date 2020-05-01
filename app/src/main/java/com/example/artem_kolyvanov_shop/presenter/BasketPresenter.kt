package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.domain.model.ProductItem.Companion.createProductItem
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class BasketPresenter : MvpPresenter<ProductsView>() {
    private val products = mutableListOf(
        createProductItem(1, "Iphone Case", "123321", 150.0, 50),
        createProductItem(2, "Samsung Case", "123321", 120.0, 30),
        createProductItem(3, "Huawei Case", "123321", 92.0,  50),
        createProductItem(4, "someProd3", "123321", 1200.0, 0),
        createProductItem(5, "someProd4", "123321", 1200.0, 0),
        createProductItem(6, "someProd5", "123321", 1200.0, 0),
        createProductItem(7, "someProd6", "123321", 1200.0, 0))

    private val product = createProductItem(6, "MacBook", "123321", 1200.0, 0)

    fun setData(){
        viewState.setProducts(products)
    }

    fun removeItem(product: ProductItem){
        val position = products.indexOf(product)
        products.remove(product)
        viewState.removeProduct(position)
    }

    fun addData(){
        viewState.addProduct(product)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()
    }

    fun onProductClick(product: ProductItem) {
        viewState.showProductDerailed(product)
    }

    override fun attachView(view: ProductsView?) {
        super.attachView(view)
        setData()
    }
}