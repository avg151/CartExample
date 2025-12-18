package com.example.cart_domain.model

data class CartItem(
    val productId: Int,
    val title: String,
    val unitPrice: Long,
    val quantity: Int,
) {
    val lineTotal: Long get() = unitPrice * quantity.toLong()
}
