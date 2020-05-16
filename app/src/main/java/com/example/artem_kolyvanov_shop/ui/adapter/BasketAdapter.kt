package com.example.artem_kolyvanov_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.products_list.*


class BasketAdapter (
    private val onDeleteClick:(pos:Int) -> Unit,
    private val onProductClick: (product: Product) -> Unit
):RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var productList: MutableList<Product> = mutableListOf()

    fun setData(list: List<Product>){
        productList = list.toMutableList()
        notifyDataSetChanged()
    }

    fun remove(pos:Int){
        productList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_list, parent, false)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bind(product: Product){
            productName.text = product.name
            productPrice.text = product.price.toString()
            discountNumber.text = "${product.discountPercent} %"

            deleteIb.setOnClickListener {
                if (adapterPosition != NO_POSITION)
                    onDeleteClick(adapterPosition)
            }
            containerView.setOnClickListener{
                onProductClick(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
