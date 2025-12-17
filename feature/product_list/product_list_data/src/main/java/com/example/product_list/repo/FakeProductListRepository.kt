package com.example.product_list.repo

import com.example.product_list_domain.model.Money
import com.example.product_list_domain.model.ProductItem
import com.example.product_list_domain.repo.ProductListRepository
import kotlinx.coroutines.delay

class FakeProductListRepository() : ProductListRepository {

    override suspend fun getProducts(): List<ProductItem> {
        delay(2_000)
        return (1..100).map { i ->
            ProductItem(
                id = i.toString(),
                title = "Product $i",
                money = Money(amount = i * 100, currency = "RUB")
            )
        }
    }
}