package com.example.cart.remote.data_source

import com.example.cart.remote.dto.CartDto

interface CartRemoteDataSource {
    suspend fun getCart(): CartDto
    suspend fun setQuantity(productId: Int, qty: Int)
}