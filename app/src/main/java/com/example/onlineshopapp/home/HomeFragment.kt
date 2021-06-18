package com.example.onlineshopapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.onlineshopapp.CategoryAdapter
import com.example.onlineshopapp.CategoryAdapterCallback
import com.example.onlineshopapp.Constants
import com.example.onlineshopapp.R
import com.example.onlineshopapp.api.Api
import com.example.onlineshopapp.model.BaseResponse
import com.example.onlineshopapp.model.CategoryModel
import com.example.onlineshopapp.model.OfferModel
import com.example.onlineshopapp.view.MainViewModel
import com.example.onlineshopapp.view.ProductAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.category_item_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe.setOnRefreshListener {
            loadData()
        }
        recyclerViewTopProduct.layoutManager=LinearLayoutManager(requireActivity())
        recyclerViewCategory.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.offersData.observe(requireActivity(), Observer {
            carouselView.setImageListener { position, imageView ->
                Glide.with(imageView).load(Constants.HOST_IMAGE+it[position].image).into(imageView)
            }
            carouselView.pageCount=it.size
        })
        viewModel.categoryData.observe(requireActivity(), Observer {
            recyclerViewCategory.adapter=CategoryAdapter(it,object :CategoryAdapterCallback{
                override fun onclickItem(item: CategoryModel) {
                    viewModel.getProductsByCategory(item.id)
                }
            })
        })
        viewModel.productData.observe(requireActivity(), Observer {
            recyclerViewTopProduct.adapter=ProductAdapter(it)
        })
        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing=it
        })
        loadData()
    }
    fun loadData(){
        viewModel.getOffers()
       viewModel.getCategories()
      viewModel.getTopProducts()

    }



    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}