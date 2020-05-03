package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.domain.model.RemoteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import java.io.IOException

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
    fun setProducts(list:List<ProductItem>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeProduct(position:Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addProduct(product:ProductItem)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showProductDerailed(product: ProductItem)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showException(msg:String)
}