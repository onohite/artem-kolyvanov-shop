package com.example.artem_kolyvanov_shop

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class ExampleUnitTest {

    @Test
    fun example() {

        val iphoneCase = Product(price = 123.5, salePercent = 30)
        val samsungCase = Product(price = 124.5, salePercent = 50)


        val products = listOf(iphoneCase,samsungCase)
        val basket = Basket(products)
        println("цена корзины - ${basket.basketPrice()}" ) //список в конструктор,метод возвращает цену
        val consolePrint = ConsolePricePrinter()
        consolePrint.print(basket)
        consolePrint.print(products)
        consolePrint.print(basket.products)
        consolePrint.print(iphoneCase.calcDiscountPrice())
        /* показал, что мы можем функциональный класс использовать для вывода продуктов и
        для вывода цены самого продукта */
    }
}

class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)
}


class Basket(val products: List<Product>) {
    /**
     * @return price for every element
     */
    fun basketPrice():Double{
        var productPrice = 0.0
        products.forEach { x ->
            productPrice += x.calcDiscountPrice()
        }
        return productPrice
    }
}

interface BasketPrinter{
    /**
     * Outputs elements from <Basket>.
     */
    fun print(basket: Basket)
}
interface ListPrinter<T>{
    /**
     * Outputs elements of list in <T> format.
     */
    fun print(list:List<T>)
}

interface PricePrinter {

    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)

}


class ConsolePricePrinter() : PricePrinter,ListPrinter<Any>,BasketPrinter {

    override fun print(price: Double) {
        println(price)
    }

    override fun print(basket: Basket) {
        basket.products.forEach { x -> println(x.calcDiscountPrice()) }
    }

    override fun print(list: List<Any>) {
        list
            .map {
                return@map if (it is Product) {
                    it.calcDiscountPrice()
                } else {
                    it
                }
            }
            .forEach { println(it) }
        }
    //немножко поигрался с языком
    }





