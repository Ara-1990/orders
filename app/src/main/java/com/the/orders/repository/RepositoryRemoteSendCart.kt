package com.the.orders.repository

import com.the.orders.db.CartEntities
import com.the.orders.db.DaoCart
import com.the.orders.remote.ProductsModelList

class RepositoryRemoteSendCart(var daoCart: DaoCart) {

    suspend fun saveProduct(item: ProductsModelList){
        val product = CartEntities(id = item.id, name = item.title, description = item.description)
        daoCart.insert(product)

    }
}