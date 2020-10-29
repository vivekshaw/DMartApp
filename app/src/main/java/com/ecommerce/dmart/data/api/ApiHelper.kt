package com.ecommerce.dmart.data.api

import com.ecommerce.dmart.data.model.Products
import com.ecommerce.dmart.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
    suspend fun getProducts(): Response<Products>
}