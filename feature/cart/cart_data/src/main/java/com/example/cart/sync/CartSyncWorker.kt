package com.example.cart.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.cart_domain.usecase.RefreshCartUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CartSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val refreshCart: RefreshCartUseCase,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        return try {
            refreshCart()
            Result.success()
        } catch (t: Throwable) {
            // Можно сделать retry только для “сетевых” ошибок, но пока просто retry
            Result.retry()
        }
    }
}