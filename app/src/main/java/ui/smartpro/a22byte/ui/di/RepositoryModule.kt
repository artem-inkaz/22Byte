package ui.smartpro.a22byte.ui.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ui.smartpro.data.db.NewsDao
import ui.smartpro.data.db.NewsDatabase
import ui.smartpro.data.repository.NewsRepo
import ui.smartpro.domain.repository.NewsRepository
import ui.smartpro.domain.repository.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        NewsDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase) = db.getNewsDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: NewsRepo,
        localDataSource: NewsDao
    ) = NewsRepositoryImpl(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideNewsRepository(repository: NewsRepositoryImpl): NewsRepository = repository
}