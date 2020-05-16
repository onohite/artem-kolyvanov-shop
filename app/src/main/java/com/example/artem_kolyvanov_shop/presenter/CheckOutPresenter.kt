package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.interactor.DeleteAllProductsFromBasketUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.DeleteAllProductsFromItemUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.GetItemsFromItemUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.GetProductsFromBasketUseCase
import com.example.artem_kolyvanov_shop.domain.model.*
import com.example.artem_kolyvanov_shop.presenter.view.CheckoutView
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CheckOutPresenter @Inject constructor(
    private val getProductsFromBasketUseCase: GetProductsFromBasketUseCase,
    private val mainApi: MainApi,
    private val getItemsFromItemUseCase: GetItemsFromItemUseCase,
    private val deleteAllProductsFromBasketUseCase: DeleteAllProductsFromBasketUseCase,
    private val deleteAllProductsFromItemUseCase: DeleteAllProductsFromItemUseCase
):BasePresenter<CheckoutView>() {
    private  val model = CreateOrderModel()

    private val products:List<Product>
        get() {
            return getProductsFromBasketUseCase.invoke()
        }

    private fun checkSymbols (text:String?):Boolean {
        val regex = """[[:alpha:]]+""".toRegex()
        return (!text.isNullOrEmpty()) && (text.length >= 3) && (regex.matches(text))
    }



    fun setData(){
        val param = !products.isNullOrEmpty()
        val totalPrice = products.sumBy { it.calcDiscountPrice()}.toString()
        model.totalPrice = totalPrice
        viewState.showTotalPrice(param,totalPrice)
    }

    private fun checkNumber (number:String?):Boolean =(!number.isNullOrEmpty()) && number
        .startsWith('+')
        .and((number.length == 12) && (number[1] == '7'))
        .or(number.startsWith('8')
            .and(number.length == 11))

    fun checkFirstName(text:String){
        if (checkSymbols(text) ) model.firstName = text
        viewState.showErrorForFirstName(!checkSymbols(text))
    }

    fun checkLastName(text:String){
        if (checkSymbols(text) ) model.lastName = text
        viewState.showErrorForLastName(!checkSymbols(text))
    }

    fun checkPhoneNumber(number:String){
        if (checkNumber(number)) model.phoneNumber = number
        viewState.showErrorForPhoneNumber(!checkNumber(number))
    }




    fun setPaymentType(id:Int){
        when(id){
            R.id.payedCash -> model.paymentType = Order.PaymentType.CashOnReceiving
            R.id.payedCard -> model.paymentType = Order.PaymentType.CardOnReceiving
        }
    }
    private fun clearOrder(){
        deleteAllProductsFromBasketUseCase()
        deleteAllProductsFromItemUseCase()
        model.totalPrice = null
        model.paymentType = null
        model.firstName = null
        model.lastName = null
        model.phoneNumber = null
        viewState.showTotalPrice(false,"")
        viewState.clearOrder()
    }


     private suspend fun orderRequest(){
         if (areAllFieldsValid()) {
             val items = getItemsFromItemUseCase()
             val order = Order(
                 model.firstName!!, model.lastName!!, model.phoneNumber!!, model.paymentType!!,
                 items = items
             )
             mainApi.sendOrder(order)
             alert("Спасибо за покупку")
             clearOrder()
         }
         else {
             alert("Заполните все поля")
         }
     }

    private fun areAllFieldsValid():Boolean{
        return checkSymbols(model.firstName) &&
                checkSymbols(model.lastName) &&
                checkNumber(model.phoneNumber) &&
                (model.paymentType != null) &&
                (model.totalPrice != null) &&
                !(products.isNullOrEmpty())
    }

    fun launchRequest(){
        launch {
            try {
                orderRequest()
            }
            catch(e: Exception){
                alert("Ошибка отправки запроса")
            }
        }
    }

    fun alert(msg:String){
        viewState.alertMsg(msg)
    }
}