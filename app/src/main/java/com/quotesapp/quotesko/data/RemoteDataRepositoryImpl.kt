package com.example.experiment.data

import com.quotesapp.quotesko.model.QuotesV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : RemoteDataRepository {
    override fun getRandomQuotes(): Flow<Response<QuotesV2>> = flow{
        emit(quoteApi.getRandomQuote())
    }.flowOn(Dispatchers.IO)
}