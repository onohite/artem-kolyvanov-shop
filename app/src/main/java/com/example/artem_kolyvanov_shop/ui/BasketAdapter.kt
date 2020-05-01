package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.ProductItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.products_list.*


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

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bind(product: ProductItem){
            productName.text = product.title
            productPrice.text = product.value.price.toString()
            discountNumber.text = "${product.value.salePercent.toString()} %"

            deleteIb.setOnClickListener {
                onDeleteClick(product)
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
