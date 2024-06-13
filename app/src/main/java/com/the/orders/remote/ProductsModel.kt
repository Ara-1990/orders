package com.the.orders.remote

import com.google.gson.annotations.SerializedName

data class ProductsModel(@SerializedName("products") val products: List<ProductsModelList>?)

data class ProductsModelList(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String)
