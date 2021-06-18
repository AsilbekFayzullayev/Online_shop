package com.example.onlineshopapp.productDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.onlineshopapp.Constants
import com.example.onlineshopapp.PrefUtils
import com.example.onlineshopapp.R
import com.example.onlineshopapp.model.ProductModel
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    lateinit var item:ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        item=intent.getSerializableExtra(Constants.EXTRA_DATA_PRODUCT)as ProductModel
        backIcon.setOnClickListener {
            finish()
        }
        cardViewFavorite.setOnClickListener {
            PrefUtils.setFavorite(item)
            if (PrefUtils.checkFavorite(item)){
                favoriteICon.setImageResource(R.drawable.ic_heart)
            }
            else{
                favoriteICon.setImageResource(R.drawable.ic_love)
            }

        }
        tvTitle.text=item.name
        tvProductName.text=item.name
        tvProductPrice.text=item.price

        if (PrefUtils.getCartCount(item)>0){
            btnAdd2Cart.visibility= View.GONE
        }
        if (PrefUtils.checkFavorite(item)){
            favoriteICon.setImageResource(R.drawable.ic_heart)
        }
        else{
            favoriteICon.setImageResource(R.drawable.ic_love)
        }
        Glide.with(this).load(Constants.HOST_IMAGE+item.image).into(imageFavorite)
        btnAdd2Cart.setOnClickListener {
            item.cartCount=1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}