package com.example.artem_kolyvanov_shop.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.artem_kolyvanov_shop.App
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.Product
import com.example.artem_kolyvanov_shop.presenter.DetailedPresenter
import com.example.artem_kolyvanov_shop.presenter.view.DetailedView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : BaseActivity(R.layout.activity_detailed),
    DetailedView {

    @Inject
    lateinit var detailedPresenter: DetailedPresenter

    private val presenter by moxyPresenter { detailedPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(detailedHeader)

        val product = intent?.getParcelableExtra<Product>(PRODUCT_TAG) ?: return
        Glide
            .with(ivDetailedImage.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher_ural_foreground)
            .into(ivDetailedImage)
        supportActionBar?.title = product.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.elevation = 30F
        tvDetailedTitle.text = product.name
        tvDetailedPrice.text = product.price.toString()
        descriptionDetailed.text = product.description
        tvDeteiledDiscount.text = """${product.discountPercent}%"""
        tvDeteiledWithDiscount.text = product.calcDiscountPrice().toString()
        productMaker.text = product.attributes.first {it.name == MAKER }.value
        productQuality.text = product.attributes.first { it.name == QUALITY }.value
        presenter.onProductShow(product)
        detailedPayButton.setOnClickListener {
            presenter.addToBasket(product)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true
    }

    companion object {
        const val PRODUCT_TAG = "PRODUCT_TAG"
        const val MAKER = "Производитель"
        const val QUALITY = "Качество"
    }

    override fun showAddedMsg() {
        Toast.makeText(this,"Добавлено", Toast.LENGTH_SHORT).show()
    }
}

