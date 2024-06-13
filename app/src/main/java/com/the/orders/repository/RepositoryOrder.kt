package com.the.orders.repository

import com.the.orders.db.CartEntities
import com.the.orders.db.DaoOrders
import com.the.orders.db.OrderEntities

class RepositoryOrder(val daoOrders: DaoOrders) {

    suspend fun saveProduct(product: CartEntities){
        var result = OrderEntities(id = product.id, name = product.name, description = product.description)
        daoOrders.insert(result)

    }

    suspend fun getOrders(): List<OrderEntities>? {
        val result = daoOrders.getOrders()
        return result
    }


}