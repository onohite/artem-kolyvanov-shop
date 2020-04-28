package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.ViewedProductDao
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.domain.model.ProductItem.Companion.createCartProduct
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductDao
): MvpPresenter<CatalogView>() {

    private val products:List<ProductItem>
        get() {
            return viewedProductDao.getAllProducts()
        }

    val list = mutableListOf(
        "Телевизоры",
        "Телефоны",
        "Планшеты",
        "Часы",
        "Компьютеры",
        "Ноутбуки")

    fun setData(){
        viewState.setCategories(list)

    }

    fun setViewedProduct(){
        viewState.setVisitedProducts(products)
    }

    fun removeItem(category:String){
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        setViewedProduct()

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setData()

    }

}
