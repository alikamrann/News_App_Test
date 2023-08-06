package com.alikamran.newsapp.data.repository.dataSource.dataSourceImpl

import com.alikamran.newsapp.data.api.NewsAPIService
import com.alikamran.newsapp.data.model.APIResponse
import com.alikamran.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
): NewsRemoteDataSource {



    override suspend fun getSearchedNews(
        searchedQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadline(searchedQuery,page)
    }
}