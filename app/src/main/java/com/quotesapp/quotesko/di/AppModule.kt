package com.quotesapp.quotesko.di

import com.example.experiment.data.QuoteApi
import com.example.experiment.data.RemoteDataRepositoryImpl
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
object AppModule {
    @Provides
    @Singleton
    fun provideQuoteApi(): QuoteApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataRepository(quoteApi: QuoteApi): RemoteDataRepositoryImpl {
        return RemoteDataRepositoryImpl(quoteApi = quoteApi)
    }
}
