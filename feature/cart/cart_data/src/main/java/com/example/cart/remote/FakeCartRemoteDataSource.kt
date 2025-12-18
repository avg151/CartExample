package com.example.cart.remote

import com.example.cart_domain.model.Cart
import com.example.cart_domain.model.CartItem
import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeCartRemoteDataSource @Inject constructor() {

    suspend fun fetchCart(): Cart {
        delay(2000)
        return Cart(
            items = listOf(
                CartItem(productId = 1, title = "Product 1", unitPrice = 100, quantity = 2),
                CartItem(productId = 2, title = "Product 2", unitPrice = 250, quantity = 1),
                CartItem(productId = 3, title = "Product 3", unitPrice = 350, quantity = 3),
                CartItem(productId = 4, title = "Product 4", unitPrice = 150, quantity = 4),
                CartItem(productId = 5, title = "Product 5", unitPrice = 250, quantity = 1),
                CartItem(productId = 6, title = "Product 6", unitPrice = 500, quantity = 6),
            )
        )
    }
}