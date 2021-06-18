package com.example.onlineshopapp

import com.example.onlineshopapp.model.CartModel
import com.example.onlineshopapp.model.ProductModel
import com.orhanobut.hawk.Hawk

object PrefUtils {
    private const val PREF_FAVORITES = "pref_favorites"
    private const val PREF_CART = "pref_cart"
    private const val PREF_FCM = "pref_fcm"


    fun setFavorite(item: ProductModel) {
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        if (items.firstOrNull { it == item.id } != null) {
            items.remove(item.id)
        } else {
            items.add(item.id)
        }
        Hawk.put(PREF_FAVORITES, items)

    }



    fun getFavoriteList(): ArrayList<Int> {
        return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    }



    fun checkFavorite(item: ProductModel): Boolean {
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        return (items.firstOrNull { it == item.id } != null)

    }


    fun setCart(item:ProductModel){
        val items=Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        val cart= items.firstOrNull { it.productId == item.id }
        if (cart!=null){
            if (item.cartCount>0){
                cart.count=item.cartCount
            }
            else{
                items.remove(cart)
            }
        }
        else{
            val newCart=CartModel(item.id,item.cartCount)
            items.add(newCart)
        }
        Hawk.put(PREF_CART,items)
    }



    fun getCartList():ArrayList<CartModel>{
        return Hawk.get(PREF_CART, arrayListOf<CartModel>())
    }



    fun getCartCount(item: ProductModel):Int{
        val items=Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf<CartModel>())
        return items.firstOrNull { it.productId == item.id }?.count?:0
    }

    fun setFCMToken(value:String){
        Hawk.put(PrefUtils.PREF_FCM,value)

    }
    fun getFCMToken():String{
        return Hawk.get(PrefUtils.PREF_FCM,"")

    }


}