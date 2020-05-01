package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewed_item.*
import kotlinx.android.synthetic.main.viewed_item.view.*
import kotlinx.android.synthetic.main.viewed_item.view.idViewed
import kotlinx.android.synthetic.main.viewed_item.view.nameViewed
import kotlinx.android.synthetic.main.viewed_item.view.priceViewed

class ViewedAdapter(): RecyclerView.Adapter<ViewedAdapter.ViewHolder>() {

    private var viewedItems:List<ProductItem> = listOf()


    fun setData(viewedProducts: List<ProductItem>){
        this.viewedItems = viewedProducts
        notifyDataSetChanged()
    }

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bind(product: ProductItem){
            idViewed.text = product.id.toString()
            nameViewed.text = product.title
            priceViewed.text = product.value.price.toString()
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