package com.example.cart.remote.error

sealed interface CartFailure {
    data class Http(val code: Int, val errorBody: String?) : CartFailure
    object Network : CartFailure
    object Serialization : CartFailure
    data class Unknown(val message: String?) : CartFailure
}