package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import kotlinx.android.synthetic.main.viewed_item.view.*

class ViewedAdapter(): RecyclerView.Adapter<ViewedAdapter.ViewHolder>() {

    private var viewedItems:List<ProductItem> = listOf()


    fun setData(viewedProducts: List<ProductItem>){
        this.viewedItems = viewedProducts
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(product: ProductItem){
            itemView.idViewed.text = product.id.toString()
            itemView.nameViewed.text = product.title
            itemView.priceViewed.text = product.value.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewedAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewed_item,parent,false)
        )


    override fun getItemCount(): Int {
        return viewedItems.size
    }

    override fun onBindViewHolder(holder: ViewedAdapter.ViewHolder, position: Int) {
        holder.bind(viewedItems[position])
    }
}