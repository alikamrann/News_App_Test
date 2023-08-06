package com.alikamran.newsapp.presentation.di

import android.app.Application
import com.alikamran.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.alikamran.newsapp.domain.usecase.SaveNewsUseCase
import com.alikamran.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideNewsViewModelFactory(
        application: Application,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        saveNewsUseCase: SaveNewsUseCase,
        getSavedNewsUseCase: GetSavedNewsUseCase,
        deleteSavedNewsUseCase: DeleteSavedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getSearchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        )
    }
}