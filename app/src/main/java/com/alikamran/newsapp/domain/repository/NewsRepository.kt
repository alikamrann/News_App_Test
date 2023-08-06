package com.alikamran.newsapp.domain.repository

import com.alikamran.newsapp.data.model.APIResponse
import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getSearchedNews(searchQuery:String,page: Int): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    suspend fun getSavedNews():Flow<List<Article>>
}