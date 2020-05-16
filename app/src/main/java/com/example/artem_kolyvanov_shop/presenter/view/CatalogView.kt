package com.example.artem_kolyvanov_shop.presenter.view

import com.example.artem_kolyvanov_shop.domain.model.*
import com.example.artem_kolyvanov_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView :MvpView{
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list:List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setVisitedProducts(list:List<Product>)

    @StateStrategyType(SkipStrategy::class)
    fun showException(msg:String)

    @StateStrategyType(SkipStrategy::class)
    fun openCategory(category: Category)

    @StateStrategyType(SkipStrategy::class)
    fun showVisited(product:Product)

    @StateStrategyType(SkipStrategy::class)
    fun openBasket()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showVisitedText()
}