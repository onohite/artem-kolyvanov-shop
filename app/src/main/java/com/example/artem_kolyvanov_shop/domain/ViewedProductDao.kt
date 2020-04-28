package com.example.artem_kolyvanov_shop.domain

import com.example.artem_kolyvanov_shop.domain.model.ProductItem

interface ViewedProductDao {

    /**
     * save this product id as viewed
     * */
    fun addProduct(productId: ProductItem)

    /**
     * get all viewed product ids
     * might be empty
     * */
    fun getAllProducts(): List<ProductItem>
}