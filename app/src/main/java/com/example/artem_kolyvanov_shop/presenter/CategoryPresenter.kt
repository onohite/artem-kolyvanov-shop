package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.ProductDao
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToBasketUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToItemUseCase
import com.example.artem_kolyvanov_shop.domain.model.MainApi
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.view.CategoryView
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CategoryPresenter @Inject constructor(
    private val mainApi: MainApi,
    private val addProductBasket: AddProductToBasketUseCase,
    private val addProductToItemUseCase: AddProductToItemUseCase
): BasePresenter<CategoryView>(){

    private var allProducts= mutableListOf<Product>()

    fun addItemToBasket(product: Product){
        val getProduct = allProducts.first{it == product}
        addProductBasket(product)
        addProductToItemUseCase(product)
        viewState.addProductToBasket(getProduct)
    }

    private fun setData(){
        viewState.setProducts(allProducts)
    }

    fun initProducts(list:List<Product>){
        allProducts = list.toMutableList()
    }

    fun onProductClick(product: Product) {
        viewState.showProductDetailed(product)
    }

    override fun attachView(view: CategoryView?) {
        super.attachView(view)
        setData()
    }

}