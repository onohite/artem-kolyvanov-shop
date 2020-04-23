package com.example.artem_kolyvanov_shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class )
interface CheckoutView :MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForLastName(visible:Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForFirstName(visible:Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhoneNumber(visible: Boolean)
}