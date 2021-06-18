package com.example.onlineshopapp.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.onlineshopapp.api.NetworkManager
import com.example.onlineshopapp.model.BaseResponse
import com.example.onlineshopapp.model.CategoryModel
import com.example.onlineshopapp.model.OfferModel
import com.example.onlineshopapp.model.ProductModel
import com.example.onlineshopapp.model.request.GetProductsByIdRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ShopRepository {
    private val compositeDisposable = CompositeDisposable()
    fun getOffers(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        success: MutableLiveData<List<OfferModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            NetworkManager.getApiService().getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<OfferModel>>>() {
                    override fun onNext(t: BaseResponse<List<OfferModel>>) {
                        progress.value = false
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )

    }

    fun getCategories(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<CategoryModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>() {
                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    fun getTopProducts(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getTopProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    fun getProductsByCategory(
        id: Int,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            NetworkManager.getApiService().getCategoryProducts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {

                    }
                })
        )
    }
    fun getProductsByIds(
        ids: List<Int>,
        error: MutableLiveData<String>,progress: MutableLiveData<Boolean>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        progress.value=true
        compositeDisposable.add(
            NetworkManager.getApiService().getTProductsByIds(GetProductsByIdRequest(ids))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                        progress.value=false
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                        progress.value=false
                    }

                    override fun onComplete() {

                    }
                })
        )
    }
}