package com.example.onlineshopapp.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlineshopapp.Constants
import com.example.onlineshopapp.R
import com.example.onlineshopapp.model.ProductModel
import com.example.onlineshopapp.productDetail.ProductDetailActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.top_product_layout.view.*

class ProductAdapter(private val items:List<ProductModel>):RecyclerView.Adapter<ProductAdapter.ItemHolder>(){
    class ItemHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.top_product_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item=items[position]
        holder.itemView.setOnClickListener {
            val intent= Intent(it.context,ProductDetailActivity::class.java)
            intent.putExtra(Constants.EXTRA_DATA_PRODUCT,item)
            it.context.startActivity(intent)

        }
        Glide.with(holder.itemView.context).load(Constants.HOST_IMAGE+item.image).into(holder.itemView.imageView1)
        holder.itemView.tvName.text=item.name
        holder.itemView.tvPrice.text=item.price

    }

    override fun getItemCount(): Int {
        return items.count()
    }
}