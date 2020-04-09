package com.example.artem_kolyvanov_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(),ProductsView {

    private val presenter = BasketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        presenter.productNameAndPricePrint()
        presenter.consolePrint()
    }

    override fun print(price: Double) {
       // Toast.makeText(this,"Общая цена со скидкой: $price",Toast.LENGTH_LONG).show()
        Log.d("Print","Общая цена со скидкой: $price")
    }

    override fun print(name: String) {
        // Toast.makeText(this, name,Toast.LENGTH_LONG).show()
        Log.d("Print",name)
    }
}

