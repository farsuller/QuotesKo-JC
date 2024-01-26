package com.quotesapp.quotesko.data

import com.quotesapp.quotesko.model.Verse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class VerseDataRepositoryImpl @Inject constructor(
    private val verseApi: VerseApiSource,
) : VerseDataRepository {
    override fun getVerse(): Flow<Response<Verse>> = flow {
        emit(verseApi.getVerse())
    }.flowOn(Dispatchers.IO)
}
