package com.example.artem_kolyvanov_shop.ui


import android.os.Bundle
import android.view.MenuItem
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.model.Product
import com.example.artem_kolyvanov_shop.presenter.BasketPresenter
import com.example.artem_kolyvanov_shop.presenter.ProductsView
import com.example.myapplication.ui.BaseActivity


class ProductActivity:BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_layout)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "О продукте"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }

        return true;
    }
}