package com.example.cart_domain.usecase

import com.example.cart_domain.repo.CartRepository

class ChangeCartItemQuantityUseCase(
    private val repo: CartRepository
) {
    suspend operator fun invoke(productId: Int, newQuantity: Int) {
        require(newQuantity >= 0) { "Quantity must be >= 0" }
        repo.setItemQuantity(productId, newQuantity)
    }
}