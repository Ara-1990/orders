package com.the.orders.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CartEntities::class, OrderEntities::class], version = 1)

abstract class Db: RoomDatabase() {

    abstract val daoCart: DaoCart
    abstract val daoOrders: DaoOrders

    companion object {
        fun create(context: Context): Db {
            return Room.databaseBuilder(context, Db::class.java, "Db_products")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
