package com.example.artem_kolyvanov_shop.presenter

import android.content.Context
import android.widget.Toast
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToBasketUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToItemUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToViewedUseCase
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.view.DetailedView
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter @Inject constructor(
    private val addProductToViewedUseCase: AddProductToViewedUseCase,
    private val addProductToBasketUseCase: AddProductToBasketUseCase,
    private val addProductToItemUseCase: AddProductToItemUseCase
) :
    MvpPresenter<DetailedView>() {

    fun onProductShow(product: Product) {
        addProductToViewedUseCase(product)
    }

    fun addToBasket(product: Product){
        addProductToBasketUseCase(product)
        addProductToItemUseCase(product)
        viewState.showAddedMsg()
    }
}