package com.alikamran.newsapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.alikamran.newsapp.data.model.APIResponse
import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.data.util.Resource
import com.alikamran.newsapp.domain.usecase.DeleteSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSavedNewsUseCase
import com.alikamran.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.alikamran.newsapp.domain.usecase.SaveNewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val savedNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase

) : AndroidViewModel(app) {



    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }

    //search
    val searchedNews: MutableLiveData<Resource<APIResponse>> = MutableLiveData()

    fun searchNews(
        searchQuery: String,
        page: Int
    ) = viewModelScope.launch {
        searchedNews.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val response = getSearchedNewsUseCase.execute(
                     searchQuery,page
                )
                searchedNews.postValue(response)

            } else {
                searchedNews.postValue(Resource.Error("No Internet connection"))
            }
        }
        catch (e : Exception){
            searchedNews.postValue(Resource.Error(e.message.toString()))
        }

    }

    //local data
    fun saveArticle(article: Article)=viewModelScope.launch {
        savedNewsUseCase.execute(article)
    }
    fun getSavedNews() = liveData{
        getSavedNewsUseCase.execute().collect{
            emit(it)
        }
    }
    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteSavedNewsUseCase.execute(article)
    }
}