package com.mindorks.framework.mvvm.data.api

import com.ecommerce.dmart.data.model.Products
import com.ecommerce.dmart.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("products")
    suspend fun getProducts(): Response<Products>

}