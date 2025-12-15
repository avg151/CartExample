package com.example.product_list_domain.repo


import com.example.product_list_domain.model.ProductItem

interface ProductListRepository {
    suspend fun getProducts(): List<ProductItem>
}