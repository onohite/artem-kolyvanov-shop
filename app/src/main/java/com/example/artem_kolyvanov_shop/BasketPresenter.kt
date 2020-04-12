package com.example.artem_kolyvanov_shop



class BasketPresenter (
    private val view: ProductsView
){
    private val iphoneCase = Product(price = 150.0, salePercent = 50, productName = "Iphone Case")
    private val samsungCase = Product(price = 120.0, salePercent = 30, productName = "Samsung Case")
    private val huaweiCase = Product(price = 92.0, salePercent = 50, productName = "Huawei Case")
    private val products = listOf(iphoneCase,samsungCase,huaweiCase)

fun consolePrint(){
    view.print(products.sumByDouble { it.calcDiscountPrice() })
}

fun productNamePrint(){
    products.forEach { product ->view.print(product.getProductName()) }
}

fun productsPrint(){
    view.print(products)
}

fun productNameAndPricePrint(){
    products.forEach { productName -> view.print("${productName.getProductName()} : ${productName.calcDiscountPrice()} р.") }
}

}