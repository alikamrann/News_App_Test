package com.alikamran.newsapp.presentation.di

import com.alikamran.newsapp.data.db.ArticleDao
import com.alikamran.newsapp.data.repository.dataSource.NewsLocalDatasource
import com.alikamran.newsapp.data.repository.dataSource.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(articleDao: ArticleDao): NewsLocalDatasource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}