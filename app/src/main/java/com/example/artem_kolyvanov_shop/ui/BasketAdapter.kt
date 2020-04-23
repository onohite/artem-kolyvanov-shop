package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.model.Product
import com.example.artem_kolyvanov_shop.R
import kotlinx.android.synthetic.main.products_list.view.*


class BasketAdapter(
    private val onDeleteClick:(product: Product) -> Unit
):RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var productList: MutableList<Product> = mutableListOf()

    fun setData(products: List<Product>){
        productList = products as MutableList<Product>
        notifyDataSetChanged()
    }

    fun addData (product:Product){
        productList.add(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_list, parent, false)
        )


    override fun onBindViewHolder(holder: BasketAdapter.ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product){
            itemView.productName.text = product.getProductName()
            itemView.productPrice.text = product.getProductPrice().toString()
            itemView.discountNumber.text = product.getProductDiscount().toString() + "%"
            itemView.deleteIb.setOnClickListener {
                onDeleteClick(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
