package com.example.network.api.safe_call

import com.example.network.api.api_result.ApiResult
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(call: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) ApiResult.Success(body)
            else ApiResult.UnknownError(NullPointerException("Response body is null"))
        } else {
            ApiResult.HttpError(
                code = response.code(),
                errorBody = response.errorBody()?.string()
            )
        }
    } catch (ce: CancellationException) {
        throw ce
    } catch (io: IOException) {
        ApiResult.NetworkError(io)
    } catch (t: Throwable) {
        //  ошибки сериализации/прочее
        ApiResult.UnknownError(t)
    }
}