package com.the.orders.db

import androidx.room.*

@Dao
interface DaoCart {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartEntities)


    @Query("SELECT * FROM `cart` ORDER BY id ASC")
    suspend fun getProducts(): MutableList<CartEntities>?


    @Delete()
    suspend fun delete(text: CartEntities)

}