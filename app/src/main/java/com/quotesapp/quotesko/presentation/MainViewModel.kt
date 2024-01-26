package com.quotesapp.quotesko.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quotesapp.quotesko.R
import com.quotesapp.quotesko.data.RemoteDataRepository
import com.quotesapp.quotesko.data.VerseDataRepository
import com.quotesapp.quotesko.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("MissingPermission")
class MainViewModel @Inject constructor(
    private val quotesRepository: RemoteDataRepository,
    private val verseDataRepository: VerseDataRepository,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
) : ViewModel() {

    val quotesResponse: MutableState<ApiState> = mutableStateOf(ApiState.Idle)
    val verseResponse: MutableState<ApiState> = mutableStateOf(ApiState.Idle)

    fun showNotification(quote: String, author: String) {
        notificationManager.notify(
            1,
            notificationBuilder
                .setContentTitle(quote)
                .setContentText(author)
                .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
                .build(),
        )
    }

    fun showNotificationExpandable(quote: String, author: String) {
        notificationManager.notify(
            1,
            notificationBuilder
                .setContentTitle(quote)
                .setContentText(author)
                .setSmallIcon(R.drawable.quotesko_ic_round_no_bg)
                .build(),
        )
    }

    fun getRandomQuotes() = viewModelScope.launch {
        quotesRepository.getRandomQuotes()
            .onStart {
                quotesResponse.value = ApiState.Loading
            }.catch {
                quotesResponse.value = ApiState.Error(it)
            }.collect {
                quotesResponse.value = ApiState.Success(it)
            }
    }

    fun getVerse() = viewModelScope.launch {
        verseDataRepository.getVerse()
            .onStart {
                verseResponse.value = ApiState.Loading
            }.catch {
                verseResponse.value = ApiState.Error(it)
            }.collect {
                verseResponse.value = ApiState.Success(it)
            }
    }
}
