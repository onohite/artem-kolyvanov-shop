package com.example.artem_kolyvanov_shop

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    private fun pricePrinter(price: Any, measure: String, discount: Double){
        if (discount == 0.0)
            println("$price/$measure")
        else
            println("$price/$measure (скидка $discount%)")
    }

    private fun typeChecker(price: Double, measure: String, discount: Double) {
        if (price%1 == 0.0)
            pricePrinter(price.toInt(),measure,discount)
        else
            pricePrinter(price,measure,discount)
    }
    private fun discounter(price: Double, discount: Double = 0.0): Double = price -  (price * discount / 100)

    private fun priceTag(price: Double, measure: String, discount: Double = 0.0) {
        typeChecker(discounter(price,discount),measure,discount)
    }

    @Test
    fun addition_isCorrect() {
        priceTag(price = 100.0, measure = "кг", discount = 15.2)
        priceTag(price = 100.0, measure = "кг")
    }
}
