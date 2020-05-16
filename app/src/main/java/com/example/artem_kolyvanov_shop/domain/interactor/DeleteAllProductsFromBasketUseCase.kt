package com.example.artem_kolyvanov_shop.domain.interactor

import com.example.artem_kolyvanov_shop.domain.ProductDao
import javax.inject.Inject

class DeleteAllProductsFromBasketUseCase @Inject constructor(
    private val productDao: ProductDao
) {
    operator fun invoke(){
        productDao.deleteAllBasketProducts()
    }
}