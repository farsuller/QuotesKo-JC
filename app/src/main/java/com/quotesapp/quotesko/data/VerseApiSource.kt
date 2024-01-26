package com.quotesapp.quotesko.data

import com.quotesapp.quotesko.model.Verse
import com.quotesapp.quotesko.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface VerseApiSource {
    @GET(Constants.END_POINT_VERSE)
    suspend fun getVerse(): Response<Verse>
}
