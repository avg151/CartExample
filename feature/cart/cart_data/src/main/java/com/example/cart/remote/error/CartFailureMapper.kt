package com.example.cart.remote.error

import com.example.network.api.api_result.ApiResult

fun ApiResult<*>.toCartFailure(): CartFailure = when (this) {
    is ApiResult.HttpError -> CartFailure.Http(code, errorBody)
    is ApiResult.NetworkError -> CartFailure.Network
    is ApiResult.SerializationError -> CartFailure.Serialization
    is ApiResult.UnknownError -> CartFailure.Unknown(exception.message)
    else -> CartFailure.Unknown("Unexpected result")
}