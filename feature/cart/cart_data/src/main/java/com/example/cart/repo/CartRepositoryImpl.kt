package com.example.cart.repo

import com.example.cart.remote.data_source.fake.FakeCartRemoteDataSource
import com.example.cart.remote.mapper.toDomain
import com.example.cart.store.CartStore
import com.example.cart_domain.model.Cart
import com.example.cart_domain.model.CartItem
import com.example.cart_domain.repo.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val store: CartStore,
    private val remote: FakeCartRemoteDataSource,
) : CartRepository {

    override fun observeCart(): Flow<Cart> =
        store.cart
            .map { it } // оставил явно для читабельности
            .distinctUntilChanged()

    override suspend fun refreshCart() {
        val serverCart = remote.getCart().toDomain()

        // Пока просто заменяем локальную корзину на серверную.
        // Позже здесь можно сделать merge/сверку/конфликт-резолв.
        store.setCart(serverCart)
    }

    override suspend fun setItemQuantity(productId: Int, quantity: Int) {
        store.update { current ->
            val items = current.items.toMutableList()
            val idx = items.indexOfFirst { it.productId == productId }

            when {
                quantity <= 0 && idx >= 0 -> items.removeAt(idx)
                quantity > 0 && idx >= 0 -> items[idx] = items[idx].copy(quantity = quantity)
                quantity > 0 && idx < 0 -> items.add(
                    // для MVP: если товара не было, добавляем с плейсхолдерами
                    // позже лучше делать через отдельную команду addItem(product)
                    CartItem(
                        productId = productId,
                        title = "Product $productId",
                        unitPrice = 100,
                        quantity = quantity
                    )
                )
            }

            current.copy(items = items)
        }
    }
}