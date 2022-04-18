package ui.smartpro.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ui.smartpro.data.model.NewsResponse
import ui.smartpro.data.utils.Constants.Companion.API_KEY
import ui.smartpro.data.utils.Constants.Companion.CountryCode
import ui.smartpro.data.utils.Constants.Companion.QUERY_PER_PAGE

interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = CountryCode,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = QUERY_PER_PAGE,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("pageSize")
        pageSize: Int = QUERY_PER_PAGE,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}