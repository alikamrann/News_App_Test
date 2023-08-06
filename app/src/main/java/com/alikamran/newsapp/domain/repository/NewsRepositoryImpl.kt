package com.alikamran.newsapp.domain.repository

import com.alikamran.newsapp.data.model.APIResponse
import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.data.repository.dataSource.NewsLocalDatasource
import com.alikamran.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.alikamran.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDatasource: NewsLocalDatasource
): NewsRepository {


    override suspend fun getSearchedNews(
        searchQuery: String,
        page: Int
    ): Resource<APIResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedNews(searchQuery,page)
        )
    }

    private fun responseToResource(response:Response<APIResponse>): Resource<APIResponse> {
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }




    override suspend fun saveNews(article: Article) {
        newsLocalDatasource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDatasource.deleteArticleFromDB(article)
    }

    override suspend fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDatasource.getSavedArticles()
    }
}