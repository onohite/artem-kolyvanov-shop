package com.example.artem_kolyvanov_shop.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.presenter.BasketPresenter
import com.example.artem_kolyvanov_shop.presenter.ProductsView
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.PRODUCT_ID
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.REQUEST_AUTH
import com.example.artem_kolyvanov_shop.ui.DetailedActivity.Companion.PRODUCT_TAG
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.basket_layout.*
import moxy.ktx.moxyPresenter

class BasketActivity:BaseActivity(),ProductsView {

    private val presenter by moxyPresenter {BasketPresenter()}

    private val basketAdapter by lazy {BasketAdapter( {
            product -> presenter.removeItem(product)},
        { product -> presenter.onProductClick(product)})}

    private var isAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)

        setSupportActionBar(findViewById(R.id.basketHeader))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productId = intent.extras?.getInt(PRODUCT_ID, -1)
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
        with(recyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = basketAdapter
        }


        addPv.setOnClickListener {
            presenter.addData()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true
    }

    override fun setProducts(list: List<ProductItem>) {
        basketAdapter.setData(list)
    }

    override fun removeProduct(position: Int) {
        basketAdapter.notifyItemRemoved(position)
    }

    override fun addProduct(product: ProductItem) {
        basketAdapter.addData(product)
    }

    override fun showProductDerailed(product: ProductItem) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(PRODUCT_TAG, product)
        })
    }
}



