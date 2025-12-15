package com.example.product_list_di

import com.example.product_list.repo.FakeProductListRepository
import com.example.product_list_domain.repo.ProductListRepository
import com.example.product_list_domain.usecase.GetProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(/* ...value = */ SingletonComponent::class)
object ProductListModule {

    @Provides
    fun provideGetProductListUseCase(
        repo: ProductListRepository
    ): GetProductListUseCase = GetProductListUseCase(repo)

    @Provides
    fun provideRepo(): ProductListRepository = FakeProductListRepository()
}
