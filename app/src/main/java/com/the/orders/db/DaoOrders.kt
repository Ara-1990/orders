package com.the.orders.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoOrders {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: OrderEntities)

    @Query("SELECT * FROM `orders` ORDER BY id ASC")
    suspend fun getOrders(): List<OrderEntities>?

}