package com.alikamran.newsapp.presentation.di

import com.alikamran.newsapp.BuildConfig
import com.alikamran.newsapp.data.api.NewsAPIService

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    private val  interceptor = HttpLoggingInterceptor().apply {
        this.level= HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
        connectTimeout(30, TimeUnit.SECONDS).
        readTimeout(30, TimeUnit.SECONDS).
        writeTimeout(30, TimeUnit.SECONDS)
    }.build()
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit): NewsAPIService {
        return retrofit.create(NewsAPIService::class.java)
    }
}