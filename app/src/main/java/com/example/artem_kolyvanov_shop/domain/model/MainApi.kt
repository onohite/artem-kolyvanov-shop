package com.example.artem_kolyvanov_shop.domain.model

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MainApi {

    @GET("products/allWithCategories/Kolyvanov/")
    suspend fun allProducts(): List<Category>

    @POST("orders/new/Kolyvanov/")
    suspend fun sendOrder(@Body order: Order)
}