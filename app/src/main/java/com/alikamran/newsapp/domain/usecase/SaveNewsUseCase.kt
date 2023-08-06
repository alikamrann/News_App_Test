package com.alikamran.newsapp.domain.usecase

import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article)=newsRepository.saveNews(article)
}