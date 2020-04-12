package com.example.artem_kolyvanov_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ProductsView {

    private val presenter = BasketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        presenter.productNameAndPricePrint()
        presenter.productPrint()
        presenter.consolePrint()
        checkoutPayButton.setOnClickListener { Toast.makeText(this,"${editText.text}, спасибо за покупку!",Toast.LENGTH_LONG).show()
        phoneNumber.text.clear()
        editText.text.clear()
        editText2.text.clear()}
    }

    override fun print(price: Double) {
       // Toast.makeText(this,"Общая цена со скидкой: $price",Toast.LENGTH_LONG).show()
        Log.d("Print","Общая цена со скидкой: $price")

    }

    override fun print(name: String) {
        // Toast.makeText(this, name,Toast.LENGTH_LONG).show()
        Log.d("Print",name)
    }

    override fun print(name: String, price: Double, discount: Int, newPrice: Double) {
        productName.text = name
        productCost.text = price.toString()
        discountNumber.text = discount.toString()
        checkoutSumValue.text = newPrice.toString()
    }

}

