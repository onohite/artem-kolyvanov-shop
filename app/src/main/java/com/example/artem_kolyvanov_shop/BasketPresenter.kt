package com.example.artem_kolyvanov_shop

class BasketPresenter (
    private val view: ProductsView
){
    private val iphoneCase = Product(price = 124.5, salePercent = 50, productName = "iphone Case")
    private val samsungCase = Product(price = 120.0, salePercent = 30, productName = "Samsung Case")
    private val products = listOf(iphoneCase,samsungCase)


fun consolePrint(){
    view.print(products.sumByDouble { it.calcDiscountPrice() })
}


fun productNamePrint(){
    products.forEach { product ->view.print(product.getProductName()) }
}

fun productPrint(){
    products.forEach { product ->view.print(product.getProductName(),product.getProductPrice(),product.getProductDiscount(),product.calcDiscountPrice()) }
}

fun productNameAndPricePrint(){
    products.forEach ({ productName -> view.print("${productName.getProductName()} : ${productName.calcDiscountPrice()} р.") })
}
}