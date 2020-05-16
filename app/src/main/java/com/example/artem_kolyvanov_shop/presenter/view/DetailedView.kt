package com.example.artem_kolyvanov_shop.presenter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class )
interface DetailedView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showAddedMsg()
}