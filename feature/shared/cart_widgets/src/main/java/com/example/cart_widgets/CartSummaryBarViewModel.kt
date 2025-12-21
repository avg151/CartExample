package com.example.cart_widgets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cart_domain.usecase.ObserveCartSummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CartSummaryBarViewModel @Inject constructor(
    observeCartSummary: ObserveCartSummaryUseCase,
    mapper: CartSummaryUiMapper,
) : ViewModel() {

    val state: StateFlow<CartSummaryUi> =
        observeCartSummary()
            .map { summary -> mapper.map(summary) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = CartSummaryUi(
                    totalItems = 0,
                    totalPriceText = "0",
                    enabled = false
                )
            )
}