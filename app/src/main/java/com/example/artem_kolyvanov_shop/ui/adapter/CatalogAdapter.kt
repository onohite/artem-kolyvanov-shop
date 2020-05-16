package com.example.artem_kolyvanov_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.artem_kolyvanov_shop.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_category.*
import javax.inject.Inject

class CatalogAdapter (
    private val openCategory:(string:String) -> Unit
): RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var categories:List<String> = listOf()

    fun setData(categories: List<String>){
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
         ViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.list_category,parent,false)
         )

    inner class ViewHolder( override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bind(text: String){
            categoryTv.text = text
            containerView.setOnClickListener {
                openCategory(text)
            }
        }
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}