package com.example.cart.store

import com.example.cart_domain.model.Cart
import kotlinx.coroutines.flow.StateFlow

interface CartStore {
    val cart: StateFlow<Cart>
    suspend fun setCart(cart: Cart)
    suspend fun update(transform: (Cart) -> Cart)
}