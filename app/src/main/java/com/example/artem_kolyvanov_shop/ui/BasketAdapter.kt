package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import kotlinx.android.synthetic.main.products_list.view.*


class BasketAdapter(
    private val onDeleteClick:(product: ProductItem) -> Unit,
    private val onProductClick: (product: ProductItem) -> Unit
):RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var productList: MutableList<ProductItem> = mutableListOf()

    fun setData(products: List<ProductItem>){
        this.productList = products as MutableList<ProductItem>
        notifyDataSetChanged()
    }

    fun addData (product:ProductItem){
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
        fun bind(product: ProductItem){
            itemView.productName.text = product.title
            itemView.productPrice.text = product.value.price.toString()
            itemView.discountNumber.text = "${product.value.salePercent.toString()} %"

            itemView.deleteIb.setOnClickListener {
                onDeleteClick(product)
            }
            itemView.setOnClickListener{
                onProductClick(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
