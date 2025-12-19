package com.example.cart.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartDto(
    val items: List<CartItemDto>
)
