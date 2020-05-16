package com.example.artem_kolyvanov_shop.presenter

import android.content.Context
import android.view.View
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToViewedUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.DeleteProductFromBasketUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.DeleteProductFromItemUseCase
import com.example.artem_kolyvanov_shop.domain.interactor.GetProductsFromBasketUseCase
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.view.ProductsView
import kotlinx.android.synthetic.main.basket_layout.*
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class BasketPresenter @Inject constructor(
    private val deleteProductFromBasketUseCase: DeleteProductFromBasketUseCase,
    private val getProductsFromBasketUseCase: GetProductsFromBasketUseCase,
    private val deleteProductFromItemUseCase: DeleteProductFromItemUseCase
) : BasePresenter<ProductsView>() {


    private val products:List<Product>
        get() {
             return getProductsFromBasketUseCase.invoke().toMutableList()
        }

    private fun setData(){
        viewState.setProducts(products)
        showTotalPrice()
    }

    fun removeItem(pos: Int){
        deleteProductFromItemUseCase(products[pos])
        deleteProductFromBasketUseCase(products[pos])
        showTotalPrice()
        viewState.removeProduct(pos)
    }

    fun showTotalPrice(){
        val checker = !products.isNullOrEmpty()
        val totalPrice = products.sumBy { it.calcDiscountPrice() }.toString()
        viewState.showTotalPrice(checker,totalPrice)
    }

    fun onProductClick(product: Product) {
        viewState.showProductDetailed(product)
    }

    override fun attachView(view: ProductsView?) {
        super.attachView(view)
        setData()
    }

}