package ui.smartpro.domain.repository

import androidx.lifecycle.LiveData
import ui.smartpro.data.model.NewsArticle
import ui.smartpro.data.model.NewsResponse
import ui.smartpro.domain.state.NetworkState

interface NewsRepository {
    suspend fun getNews(countryCode: String, pageNumber: Int): NetworkState<NewsResponse>
    suspend fun searchNews(searchQuery: String, pageNumber: Int): NetworkState<NewsResponse>
    suspend fun saveNews(news: NewsArticle): Long
    fun getSavedNews(): LiveData<List<NewsArticle>>
    suspend fun deleteNews(news: NewsArticle)
    suspend fun deleteAllNews()
}