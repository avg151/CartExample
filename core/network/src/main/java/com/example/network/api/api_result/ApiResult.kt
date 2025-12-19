package com.example.network.api.api_result

import java.io.IOException

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data class HttpError(val code: Int, val errorBody: String?) : ApiResult<Nothing>
    data class NetworkError(val exception: IOException) : ApiResult<Nothing>

    data class SerializationError(val exception: Throwable) : ApiResult<Nothing>
    data class UnknownError(val exception: Throwable) : ApiResult<Nothing>
}