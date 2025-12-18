package com.example.cart_domain.repo

import com.example.cart_domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    /** Текущее состояние корзины. Подписчики (list/detail/cart screen) получают обновления автоматически. */
    fun observeCart(): Flow<Cart>

    /** Обновить корзину по данным с сервера . */
    suspend fun refreshCart()

    /** Изменить количество товара. Репозиторий сам решит: оптимистично/сразу на сервер/в очередь. */
    suspend fun setItemQuantity(productId: Int, quantity: Int)

}