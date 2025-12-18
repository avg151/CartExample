package com.example.cart_di

import android.content.Context
import androidx.work.WorkManager
import com.example.cart.sync.WorkManagerCartSyncScheduler
import com.example.cart_domain.sync.CartSyncScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CartSyncBindingsModule {

    @Binds
    @Singleton
    abstract fun bindCartSyncScheduler(
        impl: WorkManagerCartSyncScheduler
    ): CartSyncScheduler
}

@Module
@InstallIn(SingletonComponent::class)
object CartSyncProvidesModule {

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)
}