package com.ecommerce.dmart.data.prefrences

import android.content.SharedPreferences
import javax.inject.Inject

const val STATUS="login_status"
class PreferenceHelperImpl @Inject constructor(private val preferences: SharedPreferences):PreferenceHelper {

    override fun saveLoginStatus(status: Boolean) =preferences.edit().putBoolean(STATUS,status).apply()
    override fun getLoginStatus() = preferences.getBoolean(STATUS,false)

}