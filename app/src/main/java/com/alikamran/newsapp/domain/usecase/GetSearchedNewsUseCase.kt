package com.alikamran.newsapp.domain.usecase

import com.alikamran.newsapp.data.model.APIResponse
import com.alikamran.newsapp.data.util.Resource
import com.alikamran.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery:String,page:Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(searchQuery,page)
    }
}