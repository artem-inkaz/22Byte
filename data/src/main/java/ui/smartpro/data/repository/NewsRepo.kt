package ui.smartpro.data.repository

import retrofit2.Response
import ui.smartpro.data.model.NewsResponse

interface NewsRepo {
    suspend fun searchNews(query: String, pageNumber: Int): Response<NewsResponse>
    suspend fun getNews(countryCode: String, pageNumber: Int): Response<NewsResponse>
}