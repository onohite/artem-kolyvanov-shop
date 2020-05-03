package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.ViewedProductDao
import com.example.artem_kolyvanov_shop.domain.model.MainApi
import com.example.artem_kolyvanov_shop.domain.model.ProductItem

import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.io.IOException



@InjectViewState
class CatalogPresenter(
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
): BasePresenter<CatalogView>() {

    private val products:List<ProductItem>
        get() {
            return viewedProductDao.getAllProducts()
        }

      val list = mutableListOf("")

    private fun setData(){
        viewState.setCategories(list)
    }

    private fun setViewedProduct(){
        viewState.setVisitedProducts(products)
    }

    fun removeItem(category:String){
        val position = list.indexOf(category)
        list.remove(category)
        viewState.removeItem(position)
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        setViewedProduct()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            try {
                val remoteProducts = mainApi.allProducts()
                val productNames = remoteProducts.map { remoteProduct -> remoteProduct.name }
                viewState.setCategories(productNames)
            }
            catch(e: IOException){

            }
        }
    }
}
