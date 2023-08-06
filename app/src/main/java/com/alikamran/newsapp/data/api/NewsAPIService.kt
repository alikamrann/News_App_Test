package com.alikamran.newsapp.data.api


import com.alikamran.newsapp.BuildConfig
import com.alikamran.newsapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {


    @GET("v2/everything")
    suspend fun getSearchedTopHeadline(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Response<APIResponse>

}