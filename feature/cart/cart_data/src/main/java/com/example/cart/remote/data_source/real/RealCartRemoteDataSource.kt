package com.example.cart.remote.data_source.real

import com.example.cart.remote.api.CartApi
import com.example.cart.remote.data_source.CartRemoteDataSource
import com.example.cart.remote.dto.CartDto
import com.example.cart.remote.dto.SetQtyRequestDto
import com.example.cart.remote.error.CartException
import com.example.cart.remote.error.toCartFailure
import com.example.network.api.api_result.ApiResult
import com.example.network.api.safe_call.safeApiCall
import javax.inject.Inject

class RealCartRemoteDataSource @Inject constructor(
    private val api: CartApi
) : CartRemoteDataSource {

    override suspend fun getCart(): CartDto =
        when (val r = safeApiCall { api.getCart() }) {
            is ApiResult.Success -> r.data
            else -> throw CartException(r.toCartFailure())
        }

    override suspend fun setQuantity(productId: Int, qty: Int) {
        when (val r = safeApiCall {
            api.setQty(
                productId = productId,
                body = SetQtyRequestDto(
                    productId = productId,
                    qty = qty
                )
            )
        }) {
            is ApiResult.Success -> Unit
            else -> throw CartException(r.toCartFailure())
        }
    }
}