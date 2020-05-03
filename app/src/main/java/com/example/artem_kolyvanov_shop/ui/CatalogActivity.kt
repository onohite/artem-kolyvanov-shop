package com.example.artem_kolyvanov_shop.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.data.ViewedProductDaoImpl
import com.example.artem_kolyvanov_shop.domain.model.MainApi
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.presenter.CatalogPresenter
import com.example.artem_kolyvanov_shop.presenter.CatalogView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.catalog_layout.*
import moxy.ktx.moxyPresenter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatalogActivity: BaseActivity(),CatalogView {

    private val presenter by moxyPresenter {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://207.254.71.167:9191/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(MainApi::class.java)
        CatalogPresenter(
            mainApi = service,
            viewedProductDao = ViewedProductDaoImpl(sharedPreferences)
        )
    }
    private val categoryAdapter by lazy { CategoryAdapter { category ->
        presenter.removeItem(category)}
    }
    private val viewedAdapter by lazy { ViewedAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)



        Log.d(tag, "savedInstanceState = $savedInstanceState")
        val savedInt = savedInstanceState?.getInt(SAVE_SATE_INT)
        Log.d(tag, "savedInt $savedInt")

        checkoutCatalogButton.setOnClickListener {
            val intent = Intent(this,
                BasketActivity::class.java).apply {
                putExtra(PRODUCT_ID,1000)
            }

            startActivityForResult(intent,
                REQUEST_AUTH
            )
        }
        with(categoryRV){
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        with(viewedRV){
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = viewedAdapter
        }

    }



    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_SATE_INT, 89)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (REQUEST_AUTH == requestCode){
            val isUserAuth = data?.extras?.getBoolean(IS_USER_AUTH)
            Log.d(tag, isUserAuth.toString())
        }
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val REQUEST_AUTH: Int = 10
        const val IS_USER_AUTH = "IS_USER_AUTH"
        const val SAVE_SATE_INT = "SAVE_SATE_INT"
    }

    override fun setCategories(list: List<String>) {
        categoryAdapter.setData(list)
    }

    override fun removeItem(position: Int) {
        categoryAdapter.notifyItemRemoved(position)
    }

    override fun showProductIds(productIds: List<Long>) {
        Toast.makeText(this,productIds.joinToString { "," },Toast.LENGTH_LONG).show()
    }

    override fun setVisitedProducts(list: List<ProductItem>) {
        viewedAdapter.setData(list)
    }
}

val AppCompatActivity.sharedPreferences:SharedPreferences get() =
    getSharedPreferences("data",MODE_PRIVATE)