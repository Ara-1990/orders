package com.the.orders.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteServise {

    @GET("products")
    suspend fun getProductsList(): ProductsModel

        @GET("products/search")
    suspend fun serchProducts(@Query("q")searchText:String):ProductsModel

}