package com.example.artem_kolyvanov_shop.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.model.Product
import com.example.artem_kolyvanov_shop.presenter.BasketPresenter
import com.example.artem_kolyvanov_shop.presenter.ProductsView
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.PRODUCT_ID
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.REQUEST_AUTH
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.basket_layout.*
import kotlinx.android.synthetic.main.catalog_layout.*

class BasketActivity:BaseActivity(),ProductsView {

    private val presenter = BasketPresenter()
    private val adapter = BasketAdapter { product ->
        presenter.removeItem(product)
    }

    val anime = Product(100.0,13,"Xiaomi case")
    private var isAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)

        setSupportActionBar(findViewById(R.id.basketHeader))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productId = intent.extras?.getInt(CatalogActivity.PRODUCT_ID, -1)
        Log.d(tag, productId.toString())

        supportActionBar?.title = "Корзина"
        basketPayButton.setOnClickListener {
            val intent = Intent(
                this,
                CheckoutActivity::class.java
            ).apply {
                putExtra(PRODUCT_ID, 1000)
            }

            startActivityForResult(
                intent,
                REQUEST_AUTH
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        presenter.attachView(this)
        presenter.setData()
        addPv.setOnClickListener {
            presenter.addData(anime)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true;
    }

    override fun print(price: Double) {
        TODO("Not yet implemented")
    }


    override fun print(name: String) {
        TODO("Not yet implemented")
    }

    @SuppressLint("WrongConstant")
    override fun print(products: List<Product>) {
        TODO("Not yet implemented")
    }

    override fun setProducts(list: List<Product>) {
        adapter.setData(list)
    }

    override fun removeProduct(position: Int) {
        adapter.notifyItemRemoved(position)
    }

    override fun addProduct(product: Product) {
        adapter.addData(product)
    }
}



