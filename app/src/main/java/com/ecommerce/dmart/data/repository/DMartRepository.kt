package com.ecommerce.dmart.data.repository

import com.ecommerce.dmart.data.api.ApiHelper
import com.ecommerce.dmart.data.api.SafeApiRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DMartRepository @Inject constructor(private val apiHelper: ApiHelper): SafeApiRequest() {

    suspend fun getUsers() =  flow { emit(apiRequest{apiHelper.getUsers()}) }

}