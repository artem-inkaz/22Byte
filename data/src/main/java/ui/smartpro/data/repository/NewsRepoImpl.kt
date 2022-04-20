package ui.smartpro.data.repository

import retrofit2.Response
import ui.smartpro.data.api.NewsApi
import ui.smartpro.data.model.NewsResponse
import javax.inject.Inject

class NewsRepoImpl@Inject constructor(private val newsApi: NewsApi) : NewsRepo {

    override suspend fun searchNews(query: String, pageNumber: Int): Response<NewsResponse> =
        newsApi.searchNews(query, pageNumber)

    override suspend fun getNews(countryCode: String, pageNumber: Int): Response<NewsResponse> =
        newsApi.getNews(countryCode, pageNumber)

}