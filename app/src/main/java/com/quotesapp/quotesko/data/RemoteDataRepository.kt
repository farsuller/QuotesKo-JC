package com.quotesapp.quotesko.data

import com.quotesapp.quotesko.model.QuotesV2
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataRepository {
    fun getRandomQuotes(): Flow<Response<QuotesV2>>
}
