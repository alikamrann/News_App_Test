package com.alikamran.newsapp.domain.usecase

import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }
}