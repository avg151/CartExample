package com.example.cart_di

import com.example.cart_domain.repo.CartRepository
import com.example.cart_domain.usecase.ChangeCartItemQuantityUseCase
import com.example.cart_domain.usecase.ObserveCartUseCase
import com.example.cart_domain.usecase.RefreshCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CartUseCasesDiModule {

    @Provides
    fun provideObserveCartUseCase(repo: CartRepository) = ObserveCartUseCase(repo)

    @Provides
    fun provideRefreshCartUseCase(repo: CartRepository) = RefreshCartUseCase(repo)

    @Provides
    fun provideChangeQuantityUseCase(repo: CartRepository) = ChangeCartItemQuantityUseCase(repo)
}