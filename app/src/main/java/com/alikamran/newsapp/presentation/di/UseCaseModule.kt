package com.alikamran.newsapp.presentation.di

import com.alikamran.newsapp.domain.repository.NewsRepository
import com.alikamran.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.alikamran.newsapp.domain.usecase.SaveNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {



    @Provides
    @Singleton
    fun provideSearchNewsUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideSaveNewsUseCase(
        newsRepository: NewsRepository
    ): SaveNewsUseCase {
        return SaveNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideGetSaveNewsUseCase(
        newsRepository: NewsRepository
    ): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSaveNewsUseCase(
         newsRepository: NewsRepository
    ): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(newsRepository)
    }
}