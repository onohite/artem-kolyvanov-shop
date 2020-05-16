package com.example.artem_kolyvanov_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artem_kolyvanov_shop.R
import com.example.artem_kolyvanov_shop.domain.model.Product
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.products_category.*

class CategoryAdapter(
    private val addToBasketClick:(product:Product) -> Unit,
    private val onProductClick: (product: Product) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var products:List<Product> = listOf()

    fun setData(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_category,parent,false)
        )

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(product: Product ){
            productTv.text = product.name
            addIv.setOnClickListener {
                addToBasketClick(product)
            }

            Glide
                .with(productIv.context)
                .load(product.imageUrl)
                .error(R.mipmap.ic_launcher_ural_foreground)
                .into(productIv)
            containerView.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}