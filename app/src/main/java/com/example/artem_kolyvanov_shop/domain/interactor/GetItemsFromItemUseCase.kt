package com.example.artem_kolyvanov_shop.domain.interactor

import com.example.artem_kolyvanov_shop.domain.ProductDao
import com.example.artem_kolyvanov_shop.domain.model.Order
import javax.inject.Inject

class GetItemsFromItemUseCase @Inject constructor(
    private val productDao: ProductDao
) {
    operator fun invoke():List<Order.Item>{
        return productDao.getAllOrderItems()
    }
}