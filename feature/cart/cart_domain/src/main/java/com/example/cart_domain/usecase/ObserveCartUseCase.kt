package com.example.cart_domain.usecase

import com.example.cart_domain.model.Cart
import com.example.cart_domain.repo.CartRepository
import kotlinx.coroutines.flow.Flow


class ObserveCartUseCase(
    private val repo: CartRepository
) {
    operator fun invoke(): Flow<Cart> = repo.observeCart()
}