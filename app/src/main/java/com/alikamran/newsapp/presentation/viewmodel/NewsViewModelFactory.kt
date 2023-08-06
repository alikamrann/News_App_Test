package com.alikamran.newsapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alikamran.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.alikamran.newsapp.domain.usecase.SaveNewsUseCase

class NewsViewModelFactory(
    private val app: Application,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getSearchedNewsUseCase,
            savedNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        ) as T
    }
}