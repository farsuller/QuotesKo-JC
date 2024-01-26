package com.quotesapp.quotesko.data

import com.quotesapp.quotesko.model.Verse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface VerseDataRepository {
    fun getVerse(): Flow<Response<Verse>>
}
