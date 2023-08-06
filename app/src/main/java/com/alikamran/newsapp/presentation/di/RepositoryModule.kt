package com.alikamran.newsapp.presentation.di

import com.alikamran.newsapp.data.repository.dataSource.NewsLocalDatasource
import com.alikamran.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.alikamran.newsapp.domain.repository.NewsRepository
import com.alikamran.newsapp.domain.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDatasource: NewsLocalDatasource,
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource,newsLocalDatasource)
    }
}