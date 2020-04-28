package com.example.artem_kolyvanov_shop.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.artem_kolyvanov_shop.domain.ViewedProductDao
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : ViewedProductDao {

    private var savedProductIds: List<ProductItem>
        get() {
            val historyString = sharedPreferences.getString(PRODUCT_TAG, null)
            if (historyString != null) {
                return Gson().fromJson(historyString, (object : TypeToken<List<ProductItem>>(){}).type)
            } else {
                return emptyList()
            }
        }
        set(value) {
            sharedPreferences.edit().putString(PRODUCT_TAG, Gson().toJson(value)).apply()
        }





    override fun addProduct(productId: ProductItem) {
        val productIds: List<ProductItem> = savedProductIds
        val newProductIds = mutableListOf<ProductItem>().apply {
            add(productId)
            addAll(productIds.filter { it != productId })
        }
        savedProductIds = newProductIds
    }

    override fun getAllProducts(): List<ProductItem> {
        return savedProductIds
    }

    companion object {
        private const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}