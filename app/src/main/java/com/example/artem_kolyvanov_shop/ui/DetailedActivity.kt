package com.example.artem_kolyvanov_shop.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.data.ViewedProductDaoImpl
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import com.example.artem_kolyvanov_shop.presenter.DetailedPresenter
import com.example.artem_kolyvanov_shop.presenter.DetailedView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.ktx.moxyPresenter

class DetailedActivity : BaseActivity(),
    DetailedView {

    private val presenter by moxyPresenter {
        DetailedPresenter(
            ViewedProductDaoImpl(sharedPreferences)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        val product = intent?.getParcelableExtra<ProductItem>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(ivDetailedImage)
        tvDetailedTitle.text = product.title
        tvDetailedPrice.text = product.value.calcDiscountPrice().toString()
        presenter.onProductShow(product)
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
    }
}

