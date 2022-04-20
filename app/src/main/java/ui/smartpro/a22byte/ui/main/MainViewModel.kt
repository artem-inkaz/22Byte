package ui.smartpro.a22byte.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ui.smartpro.a22byte.R
import ui.smartpro.a22byte.ui.di.CoroutinesDispatcherProvider
import ui.smartpro.common.base.BaseViewModel
import ui.smartpro.common.utils.Constants
import ui.smartpro.common.utils.NetworkHelper
import ui.smartpro.data.model.NewsArticle
import ui.smartpro.data.model.NewsResponse
import ui.smartpro.domain.repository.NewsRepository
import ui.smartpro.domain.state.NetworkState
import ui.smartpro.logging.Logger
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val repository: NewsRepository,
    private val networkHelper: NetworkHelper,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : BaseViewModel(application) {

    private val TAG = "MainViewModel"
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String>
        get() = _errorMessage

    private val _newsResponse = MutableStateFlow<NetworkState<NewsResponse>>(NetworkState.Empty())
    val newsResponse: StateFlow<NetworkState<NewsResponse>>
        get() = _newsResponse

    private val _searchNewsResponse =
        MutableStateFlow<NetworkState<NewsResponse>>(NetworkState.Empty())
    val searchNewsResponse: StateFlow<NetworkState<NewsResponse>>
        get() = _searchNewsResponse

    private var feedResponse: NewsResponse? = null
    var feedNewsPage = 1

    var searchEnable: Boolean = false
    var searchNewsPage = 1
    var searchResponse: NewsResponse? = null
    private var oldQuery: String = ""
    var newQuery: String = ""
    var totalPage = 1

    init {
        fetchNews(Constants.CountryCode)
    }

    fun fetchNews(countryCode: String) {
        if (feedNewsPage <= totalPage) {
            if (networkHelper.isNetworkConnected()) {
                viewModelScope.launch(coroutinesDispatcherProvider.io) {
                    _newsResponse.value = NetworkState.Loading()
                    when (val response = repository.getNews(countryCode, feedNewsPage)) {
                        is NetworkState.Success -> {
                            _newsResponse.value = handleFeedNewsResponse(response)
                        }
                        is NetworkState.Error -> {
                            _newsResponse.value =
                                NetworkState.Error(
                                    response.message ?: context.getString(R.string.error)
                                )
                            Logger.e(response.message ?: context.getString(R.string.error))
                        }
                        else -> {}
                    }

                }
            } else {
                _errorMessage.value = context.getString(R.string.internet_not_available)
                Logger.e(context.getString(R.string.internet_not_available))
            }
        }
    }

    private fun handleFeedNewsResponse(response: NetworkState<NewsResponse>): NetworkState<NewsResponse> {
        response.data?.let { resultResponse ->
            if (feedResponse == null) {
                feedNewsPage = 2
                feedResponse = resultResponse
            } else {
                feedNewsPage++
                val oldArticles = feedResponse?.articles
                val newArticles = resultResponse.articles
                oldArticles?.addAll(newArticles)
            }
            //Conversion
            feedResponse?.let {
                convertPublishedDate(it)
            }
            return NetworkState.Success(feedResponse ?: resultResponse)
        }
        return NetworkState.Error(context.getString(R.string.no_found_data))
    }

    fun searchNews(query: String) {
        newQuery = query
        if (newQuery.isNotEmpty() && searchNewsPage <= totalPage) {
            if (networkHelper.isNetworkConnected()) {
                viewModelScope.launch(coroutinesDispatcherProvider.io) {
                    _searchNewsResponse.value = NetworkState.Loading()
                    when (val response = repository.searchNews(query, searchNewsPage)) {
                        is NetworkState.Success -> {
                            _searchNewsResponse.value = handleSearchNewsResponse(response)
                        }
                        is NetworkState.Error -> {
                            _searchNewsResponse.value =
                                NetworkState.Error(
                                    response.message ?: context.getString(R.string.error)
                                )
                            Logger.e(response.message ?: context.getString(R.string.error))
                        }
                        else -> {
                            if (response.data?.articles?.size == 0)
                                Logger.d(context.getString(R.string.no_found_data))
                        }
                    }
                }
            } else {
                _errorMessage.value = context.getString(R.string.internet_not_available)
                Logger.e(context.getString(R.string.internet_not_available))
            }
        }
    }

    private fun handleSearchNewsResponse(response: NetworkState<NewsResponse>): NetworkState<NewsResponse> {
        response.data?.let { resultResponse ->
            if (searchResponse == null || oldQuery != newQuery) {
                searchNewsPage = 2
                searchResponse = resultResponse
                oldQuery = newQuery
            } else {
                searchNewsPage++
                val oldArticles = searchResponse?.articles
                val newArticles = resultResponse.articles
                oldArticles?.addAll(newArticles)
            }
            searchResponse?.let {
                convertPublishedDate(it)
            }
            return NetworkState.Success(searchResponse ?: resultResponse)
        }
        return NetworkState.Error(context.getString(R.string.no_found_data))
    }

    fun convertPublishedDate(currentResponse: NewsResponse) {
        currentResponse.articles.map { article ->
            article.publishedAt?.let {
                article.publishedAt = formatDate(it)
            }
        }
    }

    fun formatDate(strCurrentDate: String): String {
        var convertedDate = ""
        try {
            if (strCurrentDate.isNotEmpty() && strCurrentDate.contains("T")) {
                val countryCode = Locale.getDefault().country
                val local = Locale(countryCode)
                var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", local)
                val newDate: Date? = format.parse(strCurrentDate)

                format = SimpleDateFormat("MMM dd, yyyy hh:mm a", local)
                newDate?.let {
                    convertedDate = format.format(it)
                }
            } else {
                convertedDate = strCurrentDate
            }
        } catch (e: Exception) {
            e.message?.let {
                Logger.e(it)
            }
            convertedDate = strCurrentDate
        }
        return convertedDate
    }

    fun hideErrorToast() {
        _errorMessage.value = ""
    }

    fun saveNews(news: NewsArticle) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutinesDispatcherProvider.io + coroutineExceptionHandler) {
            repository.saveNews(news)
        }
    }

    fun getFavoriteNews() = repository.getSavedNews()

    fun deleteNews(news: NewsArticle) {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
            onError(exception)
        }
        viewModelScope.launch(coroutinesDispatcherProvider.io + coroutineExceptionHandler) {
            repository.deleteNews(news)
        }
    }

    fun clearSearch() {
        searchEnable = false
        searchResponse = null
        feedResponse = null
        feedNewsPage = 1
        searchNewsPage = 1
    }

    fun enableSearch() {
        searchEnable = true
    }

    private fun onError(throwable: Throwable) {
        throwable.message?.let {
            _errorMessage.value = it
            Logger.e(it, throwable)
        }
    }
}