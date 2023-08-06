package com.alikamran.newsapp.presentation.di

import com.alikamran.newsapp.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}