package com.example.cart.remote.mapper

import com.example.cart.remote.dto.CartDto
import com.example.cart_domain.model.Cart
import com.example.cart_domain.model.CartItem

fun CartDto.toDomain(): Cart =
    Cart(
        items = items.map {
            CartItem(
                productId = it.productId,
                title = it.title,
                unitPrice = it.unitPrice,
                quantity = it.quantity
            )
        }
    )