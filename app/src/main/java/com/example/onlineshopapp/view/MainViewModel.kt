package com.example.onlineshopapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.onlineshopapp.Constants
import com.example.onlineshopapp.api.Api
import com.example.onlineshopapp.api.NetworkManager
import com.example.onlineshopapp.api.repository.ShopRepository
import com.example.onlineshopapp.db.AppDatabase
import com.example.onlineshopapp.model.BaseResponse
import com.example.onlineshopapp.model.CategoryModel
import com.example.onlineshopapp.model.OfferModel
import com.example.onlineshopapp.model.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val repository = ShopRepository()
    val error = MutableLiveData<String>()
    val offersData = MutableLiveData<List<OfferModel>>()
    val categoryData = MutableLiveData<List<CategoryModel>>()
    val productData = MutableLiveData<List<ProductModel>>()
    var progress=MutableLiveData<Boolean>()
    fun getOffers() {
        repository.getOffers(error,progress, offersData)
    }
    fun getCategories() {
        repository.getCategories(error, categoryData)
    }
    fun getTopProducts() {
        repository.getTopProducts(error, productData)
    }
    fun getProductsByCategory(id:Int){
        repository.getProductsByCategory(id,error,productData)
    }
    fun getProductsByIds(ids:List<Int>){
        repository.getProductsByIds(ids,error,progress,productData)
    }










}
