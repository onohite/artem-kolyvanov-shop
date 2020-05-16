package com.example.artem_kolyvanov_shop.ui


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.App
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToBasketUseCase
import com.example.artem_kolyvanov_shop.domain.model.Category
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.CatalogPresenter
import com.example.artem_kolyvanov_shop.presenter.view.CatalogView
import com.example.artem_kolyvanov_shop.ui.adapter.CatalogAdapter
import com.example.artem_kolyvanov_shop.ui.adapter.ViewedAdapter
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter

import javax.inject.Inject

class CatalogActivity: BaseActivity(R.layout.catalog_layout),
    CatalogView {
    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    @Inject
    lateinit var addProductBasket: AddProductToBasketUseCase

    private val presenter by moxyPresenter { catalogPresenter }

    private val catalogAdapter by lazy {
        CatalogAdapter { category ->
            presenter.goToCategoryProducts(category)
        }
    }
    private val viewedAdapter by lazy {
        ViewedAdapter { product ->
            presenter.goToVisitedProductDetailed(
                product
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(catalogHeader)
        supportActionBar?.elevation = 30F
        supportActionBar?.title = "Каталог"
        checkoutCatalogButton.setOnClickListener {
            presenter.goToBasket()
        }

        with(categoryRV){
            layoutManager = LinearLayoutManager(context)
            adapter = catalogAdapter
        }

        with(viewedRV){
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = viewedAdapter
        }
    }


    companion object {
        const val PRODUCTS = "PRODUCTS_LIST"
        const val CATEGORY = "CATEGORY"
    }

    override fun setCategories(list: List<String>) {
        catalogAdapter.setData(list)
    }

    override fun setVisitedProducts(list: List<Product>) {
        viewedAdapter.setData(list)
    }

    override fun showException(msg:String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle(msg)
        builder.setMessage("Повторить запрос?")

        builder.setPositiveButton("повторить")
        { dialog, _ ->
            dialog.dismiss()
            this.presenter.requestLaunch()
        }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    override fun openCategory(category: Category) {
        startActivity(Intent(this, CategoryActivity::class.java).apply {
            putExtra(CATEGORY, category)
        })
    }

    override fun showVisited(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(DetailedActivity.PRODUCT_TAG, product)
        })
    }

    override fun openBasket() {
        startActivity(Intent(this,BasketActivity::class.java))
    }

    override fun showVisitedText() {
        visitedText.visibility = View.VISIBLE
    }


}

