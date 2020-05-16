package com.example.artem_kolyvanov_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.viewed_item.*

class ViewedAdapter(
    private val onProductClick: (product: Product) -> Unit
): RecyclerView.Adapter<ViewedAdapter.ViewHolder>(
) {

    private var viewedItems:List<Product> = listOf()


    fun setData(viewedProducts: List<Product>){
        this.viewedItems = viewedProducts
        notifyDataSetChanged()
    }

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bind(product: Product){
            nameViewed.text = product.name
            priceViewed.text = product.price.toString()
            discountViewed.text = product.discountPercent.toString()
            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.viewed_item,parent,false)
        )


    override fun getItemCount(): Int {
        return viewedItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewedItems[position])
    }
}