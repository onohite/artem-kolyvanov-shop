package com.example.artem_kolyvanov_shop

interface ProductsView {
    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)

    fun print(name: String)


    /**
     * Output in Name - Price - Discount - Result format
     */
    fun print(products:List<Product>)
}