package com.alikamran.newsapp.data.repository.dataSource.dataSourceImpl

import com.alikamran.newsapp.data.db.ArticleDao
import com.alikamran.newsapp.data.model.Article
import com.alikamran.newsapp.data.repository.dataSource.NewsLocalDatasource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : NewsLocalDatasource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDao.insert(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticle()
    }

    override suspend fun deleteArticleFromDB(article: Article) {
        articleDao.deleteArticle(article)
    }
}