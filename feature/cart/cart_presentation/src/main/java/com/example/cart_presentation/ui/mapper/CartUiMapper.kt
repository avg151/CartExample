package com.example.cart_presentation.ui.mapper

import com.example.cart_domain.model.Cart
import com.example.cart_presentation.ui.model.CartItemUiModel
import com.example.cart_presentation.ui.model.CartUiState
import javax.inject.Inject

class CartUiMapper @Inject constructor() {

    fun map(cart: Cart): CartUiState {
        val summary = cart.summary
        return CartUiState(
            items = cart.items.map {
                CartItemUiModel(
                    productId = it.productId,
                    title = it.title,
                    unitPrice = it.unitPrice,
                    quantity = it.quantity,
                    lineTotal = it.lineTotal
                )
            },
            totalItems = summary.totalItems,
            totalPrice = summary.totalPrice,
        )
    }
}