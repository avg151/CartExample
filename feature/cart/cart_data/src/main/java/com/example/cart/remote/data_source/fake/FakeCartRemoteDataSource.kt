package com.example.cart.remote.data_source.fake

import com.example.cart.remote.data_source.CartRemoteDataSource
import com.example.cart.remote.dto.CartDto
import com.example.cart.remote.dto.CartItemDto
import com.example.cart_domain.model.Cart
import com.example.cart_domain.model.CartItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeCartRemoteDataSource @Inject constructor() : CartRemoteDataSource {

    override suspend fun getCart(): CartDto {
        delay(2000)
        return CartDto(
            items = listOf(
                CartItemDto(productId = 1, title = "Product 1", unitPrice = 100, quantity = 2),
                CartItemDto(productId = 2, title = "Product 2", unitPrice = 250, quantity = 1),
                CartItemDto(productId = 3, title = "Product 3", unitPrice = 350, quantity = 3),
                CartItemDto(productId = 4, title = "Product 4", unitPrice = 150, quantity = 4),
                CartItemDto(productId = 5, title = "Product 5", unitPrice = 250, quantity = 1),
                CartItemDto(productId = 6, title = "Product 6", unitPrice = 500, quantity = 6),
            )
        )
    }

    override suspend fun setQuantity(productId: Int, qty: Int) {
        TODO("Not yet implemented")
    }
}