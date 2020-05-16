package com.example.artem_kolyvanov_shop.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.artem_kolyvanov_shop.App
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.interactor.AddProductToBasketUseCase
import com.example.artem_kolyvanov_shop.domain.model.Category
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.CategoryPresenter
import com.example.artem_kolyvanov_shop.presenter.view.CategoryView
import com.example.artem_kolyvanov_shop.ui.adapter.CategoryAdapter
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.category_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CategoryActivity: BaseActivity(R.layout.category_layout),
    CategoryView {
    @Inject
    lateinit var catalogPresenter: CategoryPresenter

    private val presenter by moxyPresenter { catalogPresenter }

    private val categoryAdapter by lazy {
        CategoryAdapter({ category ->
            presenter.addItemToBasket(category)
        }, { category -> presenter.onProductClick(category) })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(categoryHeader)

        val category = intent?.getParcelableExtra<Category>(CATEGORY) ?: return
        supportActionBar?.title = category.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 30F
        presenter.initProducts(category.products)

        checkoutCategoryButton.setOnClickListener {
            val intent = Intent(this,
                BasketActivity::class.java).apply {putExtra(PRODUCT_ID,1000) }
            startActivity(intent)
        }
        with(productsRV){
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true
    }

    override fun addProductToBasket(product: Product) {
        Toast.makeText(this, "Добавлено", Toast.LENGTH_SHORT).show()
    }

    override fun setProducts(list: List<Product>) {
        categoryAdapter.setData(list)
    }

    override fun showProductDetailed(product: Product) {
        startActivity(Intent(this, DetailedActivity::class.java).apply {
            putExtra(DetailedActivity.PRODUCT_TAG, product)
        })
    }

    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
        const val CATEGORY = "CATEGORY"
    }
}