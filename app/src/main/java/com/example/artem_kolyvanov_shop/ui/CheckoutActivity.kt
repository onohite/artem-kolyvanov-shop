package com.example.artem_kolyvanov_shop.ui
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.IS_USER_AUTH
import com.example.artem_kolyvanov_shop.ui.CatalogActivity.Companion.PRODUCT_ID
import com.example.artem_kolyvanov_shop.presenter.CheckOutPresenter
import com.example.artem_kolyvanov_shop.presenter.CheckoutView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.order_layout.*
import moxy.ktx.moxyPresenter


class CheckoutActivity:BaseActivity(),
    CheckoutView {

    private val presenter by moxyPresenter {   CheckOutPresenter()}
    private var isAuth: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order_layout)
        setSupportActionBar(findViewById(R.id.orderHeader))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Оформление заказа"
        setListeners()

        val productId = intent.extras?.getInt(PRODUCT_ID, -1)
        Log.d(tag, productId.toString())
        checkoutPayButton.setOnClickListener {
            isAuth = true
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(IS_USER_AUTH, isAuth)
            })
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home ) {
            finish()
        }
        return true
    }

    private fun setListeners() {
        checkoutLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        phoneNumber.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhoneNumber(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun EditText.showError(visible: Boolean) {
        val drawable = if (visible) R.drawable.ic_error
        else 0

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForFirstName(visible: Boolean) {
        checkoutFirstName.showError(visible)
    }

    override fun showErrorForPhoneNumber(visible: Boolean) {
        phoneNumber.showError(visible)
    }

    override fun showErrorForLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }
}