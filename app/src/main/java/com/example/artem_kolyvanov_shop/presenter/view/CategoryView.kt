package com.example.artem_kolyvanov_shop.presenter.view

import com.example.artem_kolyvanov_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoryView: MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun addProductToBasket(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list:List<Product>)

    @StateStrategyType(SkipStrategy::class)
    fun showProductDetailed(product: Product)
}