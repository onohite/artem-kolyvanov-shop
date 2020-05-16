package com.example.artem_kolyvanov_shop.domain.model

/**
 * Модель для создания заказа
 */
data class CreateOrderModel(
    var firstName:String ?= null,
    var lastName:String ?= null,
    var phoneNumber:String ?= null,
    var totalPrice:String ?= null,
    var paymentType: Order.PaymentType ?= null
)