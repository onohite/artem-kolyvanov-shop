package com.example.artem_kolyvanov_shop.presenter.view

import com.example.artem_kolyvanov_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class )
interface ProductsView: MvpView {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */

    /**
     * Output in Name - Price - Discount - Result format
     */

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProducts(list:List<Product>)

    @StateStrategyType(SkipStrategy::class)
    fun removeProduct(position:Int)

    @StateStrategyType(SkipStrategy::class)
    fun showProductDetailed(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTotalPrice(bool:Boolean,price:String)
}