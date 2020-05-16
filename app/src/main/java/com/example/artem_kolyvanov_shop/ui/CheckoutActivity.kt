package com.example.artem_kolyvanov_shop.ui
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.artem_kolyvanov_shop.App
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.presenter.CheckOutPresenter
import com.example.artem_kolyvanov_shop.presenter.view.CheckoutView
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.order_layout.*
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class CheckoutActivity:BaseActivity(R.layout.order_layout),
    CheckoutView {

    @Inject
    lateinit var checkOutPresenter:CheckOutPresenter

    private val presenter by moxyPresenter {checkOutPresenter}

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.orderHeader))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 30F
        supportActionBar?.title = "Оформление заказа"
        setListeners()
        presenter.setData()
        checkoutPayButton.setOnClickListener {
            presenter.launchRequest()
        }

        radioGroup.setOnCheckedChangeListener { group , checkedId -> presenter.setPaymentType(checkedId) }
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

    override fun showTotalPrice(visible: Boolean,price:String) {
        totalPriceView.text = price
        if (visible){
            textTotalPrice.visibility = View.VISIBLE
            totalPriceView.visibility = View.VISIBLE
            moneyType.visibility = View.VISIBLE
        }
        else {
            textTotalPrice.visibility = View.GONE
            totalPriceView.visibility = View.GONE
            moneyType.visibility = View.GONE
        }

    }

    override fun alertMsg(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    override fun clearOrder() {
        checkoutFirstName.text = null
        checkoutLastName.text = null
        phoneNumber.text = null
        radioGroup.clearCheck()
    }

    override fun showErrorForLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }
}