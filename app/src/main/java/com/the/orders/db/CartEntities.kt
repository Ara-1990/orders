package com.the.orders.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart")
class CartEntities(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "description")
    val description:String

)