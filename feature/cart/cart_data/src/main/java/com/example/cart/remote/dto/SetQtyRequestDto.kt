package com.example.cart.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SetQtyRequestDto(
    val productId: Int,
    val qty: Int
)


