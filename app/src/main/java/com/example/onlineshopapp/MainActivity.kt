package com.example.onlineshopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.onlineshopapp.cart.CartFragment
import com.example.onlineshopapp.db.AppDatabase
import com.example.onlineshopapp.favorite.FavoriteFragment
import com.example.onlineshopapp.home.HomeFragment
import com.example.onlineshopapp.user.UserFragment
import com.example.onlineshopapp.view.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment.newInstance()
    private val favoriteFragment = FavoriteFragment.newInstance()
    private val cartFragment = CartFragment.newInstance()
    private val userFragment = UserFragment.newInstance()
    private var activeFragment: Fragment = homeFragment
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel= MainViewModel()

        supportFragmentManager.beginTransaction().add(R.id.flContainer,homeFragment,homeFragment.tag).hide(homeFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,favoriteFragment,favoriteFragment.tag).hide(favoriteFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,cartFragment,cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.flContainer,userFragment,userFragment.tag).hide(userFragment).commit()
        supportFragmentManager.beginTransaction().show(homeFragment).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment=homeFragment
                }
                R.id.favorite -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(favoriteFragment).commit()
                    activeFragment=favoriteFragment
                }
                R.id.cart -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit()
                    activeFragment=cartFragment
                }
                R.id.user -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(userFragment).commit()
                    activeFragment=userFragment
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        fun loadData(){
            viewModel.getTopProducts()
        }





    }

    fun loadData(){
        viewModel.getTopProducts()
    }
}
