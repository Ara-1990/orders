package com.the.orders.repository

import com.the.orders.db.CartEntities
import com.the.orders.db.DaoCart

class RepositoryCart(val daoCart: DaoCart) {

    suspend fun getProducts(): MutableList<CartEntities>? {
        val result = daoCart.getProducts()
        return result
    }

    suspend fun deleteItem(item:CartEntities){
        var result = CartEntities(id = item.id, name = "", description = "")
        daoCart.delete(result)

    }


}