package com.example.artem_kolyvanov_shop.data

import android.content.SharedPreferences
import com.example.artem_kolyvanov_shop.domain.ProductDao
import com.example.artem_kolyvanov_shop.domain.model.Order
import com.example.artem_kolyvanov_shop.domain.model.Product

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductDaoImpl(  private val sharedPreferences: SharedPreferences
) : ProductDao {

    private var savedBasketProductIds: MutableList<Product>
        get() {
            val historyString = sharedPreferences.getString(PRODUCT_B, null)
            return if (historyString != null) {
                Gson().fromJson(historyString, (object : TypeToken<MutableList<Product>>(){}).type)
            } else {
                mutableListOf()
            }
        }
        set(value) {
            sharedPreferences.edit().putString(PRODUCT_B, Gson().toJson(value)).apply()
        }

    override fun addBasketProduct(productId: Product) {
        val productIds: List<Product> = savedBasketProductIds
        val newProductIds = mutableListOf<Product>().apply {
            add(productId)
            addAll(productIds)
        }
        savedBasketProductIds = newProductIds
    }

    override fun getAllBasketProducts(): List<Product> {
        return savedBasketProductIds
    }

    override fun deleteAllBasketProducts() {
        sharedPreferences.edit().remove(PRODUCT_B).apply()
    }

    override fun deleteBasketProduct(productId: Product) {
        val productIds: List<Product> = savedBasketProductIds
        val newProductIds = mutableListOf<Product>().apply {
            addAll(productIds)
        }
        newProductIds.remove(productId)
        savedBasketProductIds = newProductIds
    }

    private var savedViewedProductIds: MutableList<Product>
        get() {
            val historyString = sharedPreferences.getString(PRODUCT_V, null)
            return if (historyString != null) {
                Gson().fromJson(historyString, (object : TypeToken<MutableList<Product>>(){}).type)
            } else {
                mutableListOf()
            }
        }
        set(value) {
            sharedPreferences.edit().putString(PRODUCT_V, Gson().toJson(value)).apply()
        }

    override fun addViewedProduct(productId: Product) {
        val productIds: List<Product> = savedViewedProductIds
        val newProductIds = mutableListOf<Product>().apply {
            add(productId)
            addAll(productIds.filter { it != productId })
        }
        savedViewedProductIds = newProductIds
    }

    override fun getAllViewedProducts(): List<Product> {
        return savedViewedProductIds
    }


    private var savedOrderItems: MutableList<Order.Item>
        get() {
            val historyString = sharedPreferences.getString(ORDER_ITEMS, null)
            return if (historyString != null) {
                Gson().fromJson(historyString, (object : TypeToken<MutableList<Order.Item>>(){}).type)
            } else {
                mutableListOf()
            }
        }
        set(value) {
            sharedPreferences.edit().putString(ORDER_ITEMS, Gson().toJson(value)).apply()
        }

    override fun addOrderItem(product: Product) {
        val items:List<Order.Item> = savedOrderItems
        val newItems = mutableListOf<Order.Item>()
        if (savedOrderItems.any{ it.productId == product.id }){
            newItems.apply {
                addAll( savedOrderItems
                .filter { it.productId == product.id }
                .map {
                val newCount = 1 + it.count
                Order.Item(it.productId,newCount)})
                addAll(items.filter { it.productId != product.id })
            }
        }
        else
            newItems.apply {
                add(Order.Item(product.id,1))
                addAll(items.filter { it.productId != product.id })
            }

        savedOrderItems = newItems
    }

    override fun deleteOrderItem(product: Product) {
        val items:List<Order.Item> = savedOrderItems
        val newItems = mutableListOf<Order.Item>()

        if (savedOrderItems.any{ it.productId == product.id }){
            if (savedOrderItems.filter { it.productId == product.id }.any { it.count == 1 }){
                newItems.apply { addAll(items.filter { it.productId != product.id }) }
            }
            else {
                newItems.apply{ addAll(savedOrderItems.filter { it.productId == product.id }.map {
                    val newCount = it.count - 1
                    Order.Item(it.productId,newCount)
                }.toMutableList())}
            }
            savedOrderItems = newItems
        }
    }

    override fun getAllOrderItems(): List<Order.Item> {
        return savedOrderItems
    }

    override fun deleteAllOrderItems() {
        sharedPreferences.edit().remove(ORDER_ITEMS).apply()
    }



    companion object {
        private const val PRODUCT_V = "PRODUCT_VIEWED"
        private const val PRODUCT_B = "PRODUCT_BASKET"
        private const val ORDER_ITEMS = "ORDER_ITEMS"
    }
}