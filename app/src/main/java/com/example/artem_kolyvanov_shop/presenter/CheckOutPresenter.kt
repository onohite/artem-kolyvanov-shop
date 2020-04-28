package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.model.CreateOrderModel
import moxy.InjectViewState
import moxy.MvpPresenter


@InjectViewState
class CheckOutPresenter:MvpPresenter<CheckoutView>() {
    private val model = CreateOrderModel()

    private fun checkSymbols (text:String):Boolean = text.length < 3


    private fun checkNumber (number:String):Boolean = number
        .startsWith('+')
        .and((number.length == 12) && (number[1] == '7'))
        .or(number.startsWith('8')
            .and(number.length == 11))

    fun checkFirstName(text:String){
        if (!checkSymbols(text) ) model.firstName = text
        viewState.showErrorForFirstName(checkSymbols(text))
    }

    fun checkLastName(text:String){
        if (!checkSymbols(text) ) model.lastName = text
        viewState.showErrorForLastName(checkSymbols(text))
    }

    fun checkPhoneNumber(number:String){
        if (checkNumber(number)) model.phoneNumber = number
        viewState.showErrorForPhoneNumber(!checkNumber(number))
    }

    override fun attachView(view: CheckoutView?) {
        super.attachView(view)
    }


}