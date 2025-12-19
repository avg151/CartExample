package com.example.cart_di

import com.example.cart.remote.api.CartApi
import com.example.cart.remote.data_source.CartRemoteDataSource
import com.example.cart.remote.data_source.fake.FakeCartRemoteDataSource
import com.example.cart.remote.data_source.real.RealCartRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartRemoteModule {

    @Provides
    @Singleton
    fun provideCartApi(retrofit: Retrofit): CartApi =
        retrofit.create(CartApi::class.java)

    @Provides
    @Singleton
    @RealRemote
    fun provideRealRemote(ds: RealCartRemoteDataSource): CartRemoteDataSource = ds

    @Provides
    @Singleton
    @FakeRemote
    fun provideFakeRemote(ds: FakeCartRemoteDataSource): CartRemoteDataSource = ds

    @Provides
    @Singleton
    fun provideSelectedRemote(
        @RealRemote real: CartRemoteDataSource,
        @FakeRemote fake: CartRemoteDataSource,
    ): CartRemoteDataSource {
        return fake
    }
}