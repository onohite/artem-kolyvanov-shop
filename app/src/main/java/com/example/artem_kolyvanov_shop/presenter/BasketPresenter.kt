package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.model.CreateOrderModel
import com.example.artem_kolyvanov_shop.model.Product
import moxy.MvpPresenter


class BasketPresenter : MvpPresenter<ProductsView>() {
    private val iphoneCase = Product(
        price = 150.0,
        salePercent = 50,
        productName = "Iphone Case"
    )
    private val samsungCase = Product(
        price = 120.0,
        salePercent = 30,
        productName = "Samsung Case"
    )
    private val huaweiCase = Product(
        price = 92.0,
        salePercent = 50,
        productName = "Huawei Case"
    )
    private val products = mutableListOf(iphoneCase,samsungCase,huaweiCase)


fun consolePrint(){
    viewState.print(products.sumByDouble { it.calcDiscountPrice() })
}

fun productNamePrint(){
    products.forEach { product ->viewState.print(product.getProductName()) }
}

fun productsPrint(){
    viewState.print(products)
}

fun setData(){
    viewState.setProducts(products)
}

fun removeItem(product: Product){
    val position = products.indexOf(product)
    products.remove(product)
    viewState.removeProduct(position)
}

fun addData(product:Product){
    viewState.addProduct(product)
}

fun productNameAndPricePrint(){
    products.forEach { productName -> viewState.print("${productName.getProductName()} : ${productName.calcDiscountPrice()} р.") }
}

fun pricePrint() {
    viewState.print(iphoneCase.calcDiscountPrice())

    val allPrice: Double = 0.0
    products.forEach { product ->
        viewState.print(product.calcDiscountPrice())
    }
}

}