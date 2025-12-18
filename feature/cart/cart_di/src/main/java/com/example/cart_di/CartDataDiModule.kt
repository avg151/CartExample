package com.example.cart_di

import com.example.cart.repo.CartRepositoryImpl
import com.example.cart.store.CartStore
import com.example.cart.store.InMemoryCartStore
import com.example.cart_domain.repo.CartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartDataDiModule {

    @Binds
    @Singleton
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository

    @Binds
    @Singleton
    abstract fun bindCartStore(impl: InMemoryCartStore): CartStore
}