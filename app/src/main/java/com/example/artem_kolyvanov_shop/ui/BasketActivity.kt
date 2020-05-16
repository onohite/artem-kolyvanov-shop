package com.example.artem_kolyvanov_shop.ui


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.App
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.BasketPresenter
import com.example.artem_kolyvanov_shop.presenter.view.ProductsView
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.PRODUCTS
import com.example.artem_kolyvanov_shop.ui.DetailedActivity.Companion.PRODUCT_TAG
import com.example.artem_kolyvanov_shop.ui.adapter.BasketAdapter
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.basket_layout.*
import moxy.ktx.moxyPresenter

import javax.inject.Inject


class BasketActivity:BaseActivity(R.layout.basket_layout),
    ProductsView {

    @Inject
    lateinit var basketPresenter: BasketPresenter

    private val presenter by moxyPresenter {
        basketPresenter
    }

    private val basketAdapter by lazy {
        BasketAdapter(
            { product -> presenter.removeItem(product) },
            { product -> presenter.onProductClick(product) }
        )
    }

    private var isAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)


        setSupportActionBar(basketHeader)
        supportActionBar?.title = "Корзина"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 30F
        val productId = intent.extras?.getInt(PRODUCTS, -1)
        Log.d(tag, productId.toString())
        
        basketPayButton.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = basketAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true
    }

    override fun setProducts(list: List<Product>) {
        basketAdapter.setData(list)

    }

    override fun removeProduct(position: Int) {
        basketAdapter.remove(position)
    }

    override fun showProductDetailed(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }

    override fun showTotalPrice(bool:Boolean,price:String) {
        if (bool) {
            totalPriceText.visibility = View.VISIBLE
            totalPrice.visibility = View.VISIBLE
            moneyType.visibility = View.VISIBLE
            totalPrice.text = price
        }
        else {
            totalPriceText.visibility = View.GONE
            totalPrice.visibility = View.GONE
            moneyType.visibility = View.GONE
        }
    }
}



