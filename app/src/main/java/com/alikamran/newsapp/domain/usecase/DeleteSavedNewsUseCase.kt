package com.alikamran.newsapp.domain.usecase

import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}