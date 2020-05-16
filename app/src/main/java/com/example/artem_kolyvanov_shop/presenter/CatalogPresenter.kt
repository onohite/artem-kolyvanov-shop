package com.example.artem_kolyvanov_shop.presenter

import com.example.artem_kolyvanov_shop.domain.ProductDao
import com.example.artem_kolyvanov_shop.domain.model.*
import com.example.artem_kolyvanov_shop.presenter.view.CatalogView

import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


@InjectViewState
class CatalogPresenter @Inject constructor(
    private val productDao: ProductDao,
    private val mainApi: MainApi
): BasePresenter<CatalogView>() {

    private val viewedProducts:List<Product>
        get() {
            return productDao.getAllViewedProducts()
        }

    private var categories= mutableListOf<Category>()

      val list= mutableListOf("")

    private fun setData(){
        viewState.setCategories(categories.map { it.name })
    }

    private fun setViewedProduct(){
        if (!viewedProducts.isNullOrEmpty())
            viewState.showVisitedText()
        viewState.setVisitedProducts(viewedProducts)
    }

    fun goToCategoryProducts(name:String){
        val category = categories.first{it.name == name}
        viewState.openCategory(category)
    }

    fun goToVisitedProductDetailed(product:Product){
        viewState.showVisited(product)
    }

    fun goToBasket(){
        viewState.openBasket()
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        setViewedProduct()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        requestLaunch()
    }


    private suspend fun makeRequest(){
        val remoteCategory = mainApi.allProducts()
        categories = remoteCategory.toMutableList()
    }

    fun requestLaunch () {
        launch {
            try {
                makeRequest()
                setData()
            }
            catch(e: UnknownHostException){
                alertError("Ошибка подключения сети")
            }
            catch(e:ConnectException){
                alertError("Отсутствует подключение к сети")
            }
        }
    }

    private fun alertError(msg:String){
        viewState.showException(msg)
    }
}
