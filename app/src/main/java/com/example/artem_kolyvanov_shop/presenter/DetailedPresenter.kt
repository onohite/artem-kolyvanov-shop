package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.ViewedProductDao
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import moxy.MvpPresenter

class DetailedPresenter(private val viewedProductDao: ViewedProductDao) :
    MvpPresenter<DetailedView>() {

    fun onProductShow(product: ProductItem) {
        viewedProductDao.addProduct(product)
    }
}