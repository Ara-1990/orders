package com.the.orders.repository

import com.the.orders.remote.ProductsModel
import com.the.orders.remote.RemoteServise

class RepositoryRemote(val remoteService: RemoteServise) {

    suspend fun getProducts(): ProductsModel {
        val result = remoteService.getProductsList()
        return result
    }
}