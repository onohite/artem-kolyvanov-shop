package com.example.artem_kolyvanov_shop.domain

import com.example.artem_kolyvanov_shop.domain.model.Order
import com.example.artem_kolyvanov_shop.domain.model.Product


interface ProductDao {

    /**
     * save this product id as added
     * */
    fun addViewedProduct(productId: Product)

    /**
     * get all added product ids
     * might be empty
     * */
    fun getAllViewedProducts(): List<Product>


    /**
     * delete this product
     */
    fun deleteBasketProduct(productId: Product)

    fun addBasketProduct(productId: Product)

    fun getAllBasketProducts(): List<Product>

    fun deleteAllBasketProducts()


    fun addOrderItem(product:Product)

    fun deleteOrderItem(product:Product)

    fun getAllOrderItems():List<Order.Item>

    fun deleteAllOrderItems()
}