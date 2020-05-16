package com.example.artem_kolyvanov_shop.presenter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class )
interface CheckoutView :MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForLastName(visible:Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForFirstName(visible:Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhoneNumber(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showTotalPrice(visible:Boolean,price:String)

    @StateStrategyType(SkipStrategy::class)
    fun alertMsg(msg:String)

    @StateStrategyType(SkipStrategy::class)
    fun clearOrder()
}