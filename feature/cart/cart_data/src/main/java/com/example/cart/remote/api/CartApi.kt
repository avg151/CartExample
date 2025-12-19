package com.example.cart.remote.api

import com.example.cart.remote.dto.CartDto
import com.example.cart.remote.dto.SetQtyRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {
    @GET("cart")
    suspend fun getCart(): Response<CartDto>

    @POST("cart/items/{id}")
    suspend fun setQty(
        @Path("id") productId: Int,
        @Body body: SetQtyRequestDto
    ): Response<Unit>
}