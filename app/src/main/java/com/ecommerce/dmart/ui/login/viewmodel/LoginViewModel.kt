package com.ecommerce.dmart.ui.login.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.dmart.data.model.User
import com.ecommerce.dmart.data.prefrences.PreferenceHelperImpl
import com.ecommerce.dmart.data.repository.DMartRepository
import com.ecommerce.dmart.util.NetworkUtil
import com.ecommerce.dmart.util.Resource
import com.ecommerce.dmart.util.toast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val mainRepository: DMartRepository,
    private val networkHelper: NetworkUtil,
    @ApplicationContext private val applicationContext: Context,
    private val preferenceHelperImpl: PreferenceHelperImpl
) : ViewModel() {

    /*init {
        getProducts()
    }*/
    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    private fun test() = viewModelScope.launch {
        preferenceHelperImpl.saveLoginStatus(true)
    }

    private fun getUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getUsers()
                    .catch { e ->
                        _users.postValue(Resource.error(e.toString(), null))
                    }
                    .collect {
                        _users.postValue(Resource.success(it))
                    }

                /* if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                 } else _users.postValue(Resource.error(it.errorBody().toString(), null))*/

            } else {
                _users.postValue(Resource.error("No internet connection", null))
            }
        }
    }


    fun getProducts() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getProducts()
                    .catch { e ->
                        applicationContext.toast(e.toString())
                    }
                    .collect {
                        applicationContext.toast("Size-" + it.products.size)
                    }
            }
        }
    }
}
