package com.example.artem_kolyvanov_shop.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.product_layout.view.*

class CategoryAdapter(
    private val onDeleteClick:(string:String) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories:List<String> = listOf()

    fun setData(categories: List<String>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder =
         ViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.item_category,parent,false)
         )

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(text: String){
            itemView.categoryTv.text = text
            itemView.deleteIv.setOnClickListener {
                onDeleteClick(text)
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}