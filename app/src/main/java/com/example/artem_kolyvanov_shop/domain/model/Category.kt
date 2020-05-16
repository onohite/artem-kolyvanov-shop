package com.example.artem_kolyvanov_shop.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode

@Parcelize
data class Category(
    val name: String,
    val products: List<Product>
):Parcelable

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
):Parcelable {

    fun calcDiscountPrice():Int {
        val calcPrice =  price * (1 - (discountPercent / 100.0))
        return getRounded(calcPrice)
    }

    private fun getRounded(price:Double): Int = price.toBigDecimal()
        .setScale(0, RoundingMode.CEILING).toInt()


    @Parcelize
    data class Attribute(
        val name: String,
        val value: String
    ):Parcelable
}