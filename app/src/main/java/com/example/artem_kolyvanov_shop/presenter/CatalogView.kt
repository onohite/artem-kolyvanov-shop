package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView :MvpView{
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategories(list:List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(position:Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProductIds(productIds: List<Long>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setVisitedProducts(list:List<ProductItem>)
}