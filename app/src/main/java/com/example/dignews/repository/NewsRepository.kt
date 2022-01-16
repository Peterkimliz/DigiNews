package com.example.dignews.repository

import android.content.Context
import com.example.dignews.db.ArticleDatabase
import com.example.dignews.models.Articles
import com.example.dignews.network.RetrofitInstance

class NewsRepository(context: Context) {
   private val db = ArticleDatabase(context.applicationContext)
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsertArticle(article: Articles) = db.getArticles().upInsert(article)
    fun getArticle() = db.getArticles().getAllData()
    suspend fun deleteArticle(article: Articles) = db.getArticles().deleteArticle(article)
}