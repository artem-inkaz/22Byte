package ui.smartpro.a22byte.ui.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ui.smartpro.a22byte.BuildConfig
import ui.smartpro.data.api.NewsApi
import ui.smartpro.data.repository.NewsRepo
import ui.smartpro.data.repository.NewsRepoImpl
import ui.smartpro.a22byte.utils.Constants
import ui.smartpro.common.utils.Constants.Companion.BASE_URL
import ui.smartpro.common.utils.Constants.Companion.searchTimeDelay
import ui.smartpro.logging.Logger
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val TAG = "NewsApp"

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Logger.d(message)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(searchTimeDelay, TimeUnit.MILLISECONDS)
            .readTimeout(searchTimeDelay, TimeUnit.MILLISECONDS)
            .writeTimeout(searchTimeDelay, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepo(newsRepo: NewsRepoImpl): NewsRepo = newsRepo

}