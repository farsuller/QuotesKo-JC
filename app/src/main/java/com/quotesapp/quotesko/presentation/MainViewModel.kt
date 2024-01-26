package com.quotesapp.quotesko.presentation

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.experiment.data.RemoteDataRepository
import com.quotesapp.quotesko.R
import com.quotesapp.quotesko.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@SuppressLint("MissingPermission")
class MainViewModel @Inject constructor(
    private val repository: RemoteDataRepository,
    private val notificationBuilder: NotificationCompat.Builder,
    private val notificationManager: NotificationManagerCompat,
) : ViewModel() {

    val response: MutableState<ApiState> = mutableStateOf(ApiState.Idle)

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
        repository.getRandomQuotes()
            .onStart {
                response.value = ApiState.Loading
            }.catch {
                response.value = ApiState.Error(it)
            }.collect {
                response.value = ApiState.Success(it)
            }
    }
}
