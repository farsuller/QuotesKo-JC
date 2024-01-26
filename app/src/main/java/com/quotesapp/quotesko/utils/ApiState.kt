package com.quotesapp.quotesko.utils

import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<*>) : ApiState()
    data class Error(val error: Throwable) : ApiState()
    object Loading : ApiState()
    object Idle : ApiState()
}
