package com.example.onlineshopapp.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopapp.PrefUtils
import com.example.onlineshopapp.R
import com.example.onlineshopapp.view.MainViewModel
import com.example.onlineshopapp.view.ProductAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {


    lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)



        viewModel.productData.observe(requireActivity(), Observer {
            recyclerProducts.adapter=ProductAdapter(it)
        })


        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.progress.observe(requireActivity(), Observer {
            swipe2.isRefreshing=it
        })
    }
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerProducts.layoutManager= LinearLayoutManager(requireActivity())
        swipe2.setOnRefreshListener {
            loadData()
        }

       loadData()
    }
   private fun loadData(){ viewModel.getProductsByIds(PrefUtils.getFavoriteList())}

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteFragment()
    }
}