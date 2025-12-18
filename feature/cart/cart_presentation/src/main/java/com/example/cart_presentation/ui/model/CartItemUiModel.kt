package com.example.cart_presentation.ui.model

data class CartItemUiModel(
    val productId: Int,
    val title: String,
    val unitPrice: Long,
    val quantity: Int,
    val lineTotal: Long,
)