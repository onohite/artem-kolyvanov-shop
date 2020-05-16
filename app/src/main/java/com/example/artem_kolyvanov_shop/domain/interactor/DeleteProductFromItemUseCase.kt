package com.example.artem_kolyvanov_shop.domain.interactor

import com.example.artem_kolyvanov_shop.domain.ProductDao
import com.example.artem_kolyvanov_shop.domain.model.Product
import javax.inject.Inject

class DeleteProductFromItemUseCase @Inject constructor(
    private val productDao: ProductDao
) {

    operator fun invoke(item:Product){
        productDao.deleteOrderItem(item)
    }
}