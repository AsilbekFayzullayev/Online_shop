package com.example.onlineshopapp.cart

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopapp.MakeOrderActivity
import com.example.onlineshopapp.PrefUtils
import com.example.onlineshopapp.R
import com.example.onlineshopapp.view.MainViewModel
import kotlinx.android.synthetic.main.fragment_cart.*

class CartFragment : Fragment() {
   lateinit var  viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing=it
        })
        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.productData.observe(requireActivity(), Observer {
            recycler.adapter=CartAdapter(it)
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager=LinearLayoutManager(requireActivity())
        swipe.setOnRefreshListener {
            loadData()
        }
        loadData()


        btnMakeOrder.setOnClickListener {
            startActivity(Intent(requireActivity(),MakeOrderActivity()::class.java))
        }



    }
   private fun loadData(){
        viewModel.getProductsByIds(PrefUtils.getCartList().map { it.productId })
    }
}