package com.example.cart_domain.usecase

import com.example.cart_domain.repo.CartRepository

class RefreshCartUseCase(
    private val repo: CartRepository
) {
    suspend operator fun invoke() = repo.refreshCart()
}