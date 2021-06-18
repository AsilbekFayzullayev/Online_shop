package com.example.onlineshopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlineshopapp.model.CategoryModel
import com.example.onlineshopapp.model.ProductModel

@Database(entities = [CategoryModel::class,ProductModel::class],version = 1)
 abstract class AppDatabase:RoomDatabase(){
    abstract fun productDao():ProductDao
    abstract fun categoryDao():CategoryDao
companion object {
    var INSTANCE:AppDatabase?=null

    fun initDatabase(context: Context){
        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,"online_shop_dv").build()
        }

    }
    fun getDatabase():AppDatabase{
        return INSTANCE!!
    }
}
}