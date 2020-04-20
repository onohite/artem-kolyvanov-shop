package com.example.artem_kolyvanov_shop.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.model.Product
import com.example.artem_kolyvanov_shop.presenter.BasketPresenter
import com.example.artem_kolyvanov_shop.presenter.ProductsView
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.PRODUCT_ID
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.REQUEST_AUTH
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.basket_layout.*
import recyclerViewAdapter

class BasketActivity:BaseActivity(),ProductsView {

    private val presenter = BasketPresenter()
    private var isAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basket_layout)

        presenter.attachView(this)
        setSupportActionBar(findViewById(R.id.basketHeader))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productId = intent.extras?.getInt(CatalogActivity.PRODUCT_ID, -1)
        Log.d(tag, productId.toString())
        presenter.productsPrint()

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
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        val adapter = recyclerViewAdapter(products)
        recyclerView.adapter = adapter
    }

    override fun showErrorForLastName(visible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showErrorForFirstName(visible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showErrorForPhoneNumber(visible: Boolean) {
        TODO("Not yet implemented")
    }
}



