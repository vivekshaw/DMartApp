package com.ecommerce.dmart.di

import android.content.Context
import android.content.SharedPreferences
import com.ecommerce.dmart.BuildConfig
import com.ecommerce.dmart.application.DMartApplication
import com.ecommerce.dmart.data.api.ApiHelper
import com.ecommerce.dmart.data.localdb.database.AppDatabase
import com.ecommerce.dmart.data.prefrences.PreferenceHelper
import com.ecommerce.dmart.data.prefrences.PreferenceHelperImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mindorks.framework.mvvm.data.api.ApiHelperImpl
import com.mindorks.framework.mvvm.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideApplication() = DMartApplication()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext appContext: Context):SharedPreferences = appContext.getSharedPreferences("DMart", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun providePreferenceHelper(prefHelper: PreferenceHelperImpl): PreferenceHelper = prefHelper

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

}