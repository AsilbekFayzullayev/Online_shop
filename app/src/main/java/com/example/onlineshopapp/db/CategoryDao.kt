package com.example.onlineshopapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onlineshopapp.model.CategoryModel

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items:List<CategoryModel>)

    @Query("select*from category")
    fun getAllCategories():List<CategoryModel>
}