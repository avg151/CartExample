package com.example.cart_domain.model

data class Cart(
    val items: List<CartItem>,
) {
    val summary: CartSummary
        get() {
            val totalItems = items.sumOf { it.quantity }
            val totalPrice = items.sumOf { it.lineTotal }
            return CartSummary(
                totalItems = totalItems,
                totalPrice = totalPrice
            )
        }
}
