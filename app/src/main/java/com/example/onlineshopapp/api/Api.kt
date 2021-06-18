package com.example.onlineshopapp.api

import com.example.onlineshopapp.model.BaseResponse
import com.example.onlineshopapp.model.CategoryModel
import com.example.onlineshopapp.model.OfferModel
import com.example.onlineshopapp.model.ProductModel
import com.example.onlineshopapp.model.request.GetProductsByIdRequest
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("get_offers")
    fun getOffers():Observable<BaseResponse<List<OfferModel>>>
    @GET("get_categories")
    fun getCategories():Observable<BaseResponse<List<CategoryModel>>>
    @GET("get_top_products")
    fun getTopProducts():Observable<BaseResponse<List<ProductModel>>>
    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId:Int):Observable<BaseResponse<List<ProductModel>>>
    @POST("get_products_by_ids")
    fun getTProductsByIds(@Body request:GetProductsByIdRequest):Observable<BaseResponse<List<ProductModel>>>
}