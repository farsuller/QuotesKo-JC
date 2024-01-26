package com.quotesapp.quotesko.utils

import com.quotesapp.quotesko.model.QuotesV2
import retrofit2.Response

sealed class ApiState {
    data class Success(val data: Response<QuotesV2>) : ApiState()
    data class Error(val error: Throwable) : ApiState()
    object Loading : ApiState()
    object Idle : ApiState()
}
