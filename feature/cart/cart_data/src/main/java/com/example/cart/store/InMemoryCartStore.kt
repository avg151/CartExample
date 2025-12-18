package com.example.cart.store

import com.example.cart_domain.model.Cart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject


class InMemoryCartStore @Inject constructor() : CartStore {

    private val mutex = Mutex()
    private val _cart = MutableStateFlow(Cart(items = emptyList()))

    override val cart: StateFlow<Cart> = _cart

    override suspend fun setCart(cart: Cart) {
        mutex.withLock {
            _cart.value = cart
        }
    }

    override suspend fun update(transform: (Cart) -> Cart) {
        mutex.withLock {
            _cart.update(transform)
        }
    }
}