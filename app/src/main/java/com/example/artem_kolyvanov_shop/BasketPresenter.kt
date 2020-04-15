package com.example.artem_kolyvanov_shop

import moxy.MvpPresenter


class BasketPresenter : MvpPresenter<ProductsView>() {
    private val iphoneCase = Product(price = 150.0, salePercent = 50, productName = "Iphone Case")
    private val samsungCase = Product(price = 120.0, salePercent = 30, productName = "Samsung Case")
    private val huaweiCase = Product(price = 92.0, salePercent = 50, productName = "Huawei Case")
    private val products = listOf(iphoneCase,samsungCase,huaweiCase)

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

fun consolePrint(){
    viewState.print(products.sumByDouble { it.calcDiscountPrice() })
}

fun productNamePrint(){
    products.forEach { product ->viewState.print(product.getProductName()) }
}

fun productsPrint(){
    viewState.print(products)
}

fun productNameAndPricePrint(){
    products.forEach { productName -> viewState.print("${productName.getProductName()} : ${productName.calcDiscountPrice()} р.") }
}

}