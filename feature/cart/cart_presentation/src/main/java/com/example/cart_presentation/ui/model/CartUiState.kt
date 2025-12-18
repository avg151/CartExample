package com.example.cart_presentation.ui.model

data class CartUiState(
    val items: List<CartItemUiModel> = emptyList(),
    val totalItems: Int = 0,
    val totalPrice: Long = 0L,
    val isRefreshing: Boolean = false,
    val error: String? = null,
)