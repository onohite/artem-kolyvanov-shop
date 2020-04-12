package com.example.artem_kolyvanov_shop

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import recyclerViewAdapter


class MainActivity : AppCompatActivity(),ProductsView {

    private val presenter = BasketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.productsPrint()
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

    override fun print(products: List<Product>) {
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false)

        val adapter = recyclerViewAdapter(products)
        recyclerView.setAdapter(adapter)
    }

}

