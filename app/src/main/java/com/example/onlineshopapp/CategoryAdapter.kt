package com.example.onlineshopapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineshopapp.model.CategoryModel
import kotlinx.android.synthetic.main.category_item_layout.view.*
interface CategoryAdapterCallback{
    fun onclickItem(item:CategoryModel)
}

class CategoryAdapter(private val items:List<CategoryModel>,private val callback: CategoryAdapterCallback):RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    class ItemHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
       return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return items.count()
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.itemView.setOnClickListener {
            items.forEach {
                it.checked=false
            }
            item.checked=true
            callback.onclickItem(item)
            notifyDataSetChanged()}
            holder.itemView.categoryTv.text=item.title
            if (item.checked){
                holder.itemView.cardViewCategory
                    .setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.colorPrimary))
                holder.itemView.categoryTv.setTextColor(Color.WHITE)
            }
            else{
                holder.itemView.cardViewCategory.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.grayOne))
                holder.itemView.categoryTv.setTextColor(Color.BLACK)
                holder.itemView.categoryTv.setTextColor(Color.WHITE)

            }


    }
}