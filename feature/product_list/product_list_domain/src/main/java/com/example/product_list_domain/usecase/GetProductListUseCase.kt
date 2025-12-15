package com.example.product_list_domain.usecase


import com.example.product_list_domain.repo.ProductListRepository

class GetProductListUseCase (
    private val repo: ProductListRepository
) {
    suspend operator fun invoke(): List<String> {
        return repo.getProducts()
            .map { it.title }
    }
}
