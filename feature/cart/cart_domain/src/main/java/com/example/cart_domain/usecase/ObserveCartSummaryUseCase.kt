package com.example.cart_domain.usecase

import com.example.cart_domain.model.CartSummary
import com.example.cart_domain.repo.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ObserveCartSummaryUseCase (
    private val repo: CartRepository
) {
    operator fun invoke(): Flow<CartSummary> =
        repo.observeCart().map { cart ->
            cart.summary
        }
}