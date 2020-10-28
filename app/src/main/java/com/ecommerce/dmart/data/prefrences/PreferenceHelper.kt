package com.ecommerce.dmart.data.prefrences

interface PreferenceHelper{
    fun saveLoginStatus(status:Boolean)
    fun getLoginStatus():Boolean
}