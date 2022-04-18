package ui.smartpro.data.utils

import ui.smartpro.a22byte.BuildConfig

class Constants {
    companion object {
        const val API_KEY = BuildConfig.NEWS_API_KEY
        const val BASE_URL = "https://newsapi.org"
        const val searchTimeDelay = 500L
        const val QUERY_PER_PAGE = 20
        const val DEFAULT_PAGE_INDEX = 1
        const val CountryCode = "ru"
    }
}