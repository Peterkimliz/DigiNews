package com.example.dignews.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dignews.models.Articles
import com.example.dignews.models.NewsResponse
import com.example.dignews.repository.NewsRepository
import com.example.trendnews.utils.Resource
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private var newsRepository: NewsRepository = NewsRepository(application)
    private var _breakingNews = MutableLiveData<Resource<NewsResponse?>>()
    val breakingNews: LiveData<Resource<NewsResponse?>> get() = _breakingNews
    var breakingNewsPage = 1

    private var _searchNews = MutableLiveData<Resource<NewsResponse?>>()
    val searchNews: LiveData<Resource<NewsResponse?>> get() = _searchNews
    var searchNewsPage = 1

    init {
        getBreakingNews("us")
    }

    ///////////////////////////////function to get breakingNews ////////////////////////////////////////
    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            _breakingNews.postValue(Resource.Loading())
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            _breakingNews.postValue(Resource.Success(response.body()))
        } catch (e: Exception) {

            _breakingNews.postValue(Resource.Error(e.message!!, null))
        }
    }

    ///////////////////////////////function to search news /////////////////////////////////////////////
    fun searchNews(searchQuery: String) = viewModelScope.launch {
        try {
            _searchNews.postValue(Resource.Loading())
            val response = newsRepository.searchNews(searchQuery, searchNewsPage)
            _searchNews.postValue(Resource.Success(response.body()))

        } catch (e: Exception) {
            _searchNews.postValue(Resource.Error(e.message!!, null))

        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////function for interacting with room database/////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

fun saveArticle(articles: Articles)=viewModelScope.launch {
    newsRepository.upsertArticle(articles)
}

fun getSavedArrticle()=newsRepository.getArticle()

fun deleteArticle(articles: Articles)=viewModelScope.launch {
    newsRepository.deleteArticle(articles)
}


}