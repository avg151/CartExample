package com.example.cart.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CartItemDto(
    val title: String,
    val quantity: Int,
    @SerialName("product_id") val productId: Int,
    @SerialName("unit_price") val unitPrice: Long,
)
