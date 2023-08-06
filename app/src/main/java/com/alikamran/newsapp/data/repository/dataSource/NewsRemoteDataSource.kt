package com.alikamran.newsapp.data.repository.dataSource

import com.alikamran.newsapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getSearchedNews(
        searchedQuery: String,
        page: Int
    ): Response<APIResponse>
}