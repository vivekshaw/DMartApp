package com.mindorks.framework.mvvm.data.api
import com.ecommerce.dmart.data.api.ApiHelper
import com.ecommerce.dmart.data.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}