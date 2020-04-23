package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class )
interface ProductsView: MvpView {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(price: Double)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(name: String)

    /**
     * Output in Name - Price - Discount - Result format
     */
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(products:List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list:List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProduct(position:Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProduct(product:Product)
}