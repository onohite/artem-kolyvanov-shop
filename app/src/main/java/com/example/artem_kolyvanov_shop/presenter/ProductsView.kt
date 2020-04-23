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
    fun print(price: Double)

    fun print(name: String)


    /**
     * Output in Name - Price - Discount - Result format
     */
    fun print(products:List<Product>)

    fun showErrorForLastName(visible:Boolean)

    fun showErrorForFirstName(visible:Boolean)

    fun showErrorForPhoneNumber(visible: Boolean)
}