package com.example.artem_kolyvanov_shop

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import recyclerViewAdapter


class MainActivity : AppCompatActivity(),ProductsView {

    private val presenter = BasketPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        setListener()
        presenter.productsPrint()
        checkoutPayButton.setOnClickListener { Toast.makeText(this,"${checkoutFirstName.text}, спасибо за покупку!",Toast.LENGTH_LONG).show()
            phoneNumber.text.clear()
            checkoutLastName.text.clear()
            checkoutFirstName.text.clear()}

    }




    fun setListener(){
        checkoutLastName.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        checkoutFirstName.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        phoneNumber.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhoneNumber(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun EditText.showError(visible:Boolean){
        val drawable = if (visible) R.drawable.ic_error
        else 0
        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun print(price: Double) {
       // Toast.makeText(this,"Общая цена со скидкой: $price",Toast.LENGTH_LONG).show()
        Log.d("Print","Общая цена со скидкой: $price")

    }

    override fun print(name: String) {
        // Toast.makeText(this, name,Toast.LENGTH_LONG).show()
        Log.d("Print",name)
    }

    override fun print(products: List<Product>) {
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        val adapter = recyclerViewAdapter(products)
        recyclerView.setAdapter(adapter)
    }

    override fun showErrorForLastName(visible: Boolean) {
        checkoutLastName.showError(visible)
    }


    override fun showErrorForFirstName(visible: Boolean) {
        checkoutFirstName.showError(visible)
    }

    override fun showErrorForPhoneNumber(visible: Boolean) {
        phoneNumber.showError(visible)
    }


}

