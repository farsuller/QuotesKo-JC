package com.quotesapp.quotesko.di

import com.quotesapp.quotesko.data.QuoteApi
import com.quotesapp.quotesko.data.RemoteDataRepositoryImpl
import com.quotesapp.quotesko.data.VerseApiSource
import com.quotesapp.quotesko.data.VerseDataRepositoryImpl
import com.quotesapp.quotesko.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_QUOTES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataRepository(quoteApi: QuoteApi): RemoteDataRepositoryImpl {
        return RemoteDataRepositoryImpl(quoteApi = quoteApi)
    }

    @Provides
    @Singleton
    fun provideVerseApiSource(): VerseApiSource {
        return Retrofit.Builder().baseUrl(Constants.BASE_VERSE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VerseApiSource::class.java)
    }

    @Provides
    @Singleton
    fun provideVerseDataRepository(verseApiSource: VerseApiSource): VerseDataRepositoryImpl {
        return VerseDataRepositoryImpl(verseApi = verseApiSource)
    }
}
