package com.example.cart_presentation.vm


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cart_domain.usecase.ChangeCartItemQuantityUseCase
import com.example.cart_domain.usecase.ObserveCartUseCase
import com.example.cart_domain.usecase.RefreshCartUseCase
import com.example.cart_presentation.ui.mapper.CartUiMapper
import com.example.cart_presentation.ui.model.CartUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel @Inject constructor(
    observeCart: ObserveCartUseCase,
    private val changeQuantity: ChangeCartItemQuantityUseCase,
    private val refreshCart: RefreshCartUseCase,
    private val mapper: CartUiMapper,
) : ViewModel() {

    private val _state = MutableStateFlow(CartUiState())
    val state: StateFlow<CartUiState> = _state

    init {
        viewModelScope.launch {
            observeCart()
                .catch { t ->
                    _state.update { it.copy(error = t.message ?: "Unknown error") }
                }
                .collect { cart ->
                    _state.value = mapper.map(cart).copy(
                        isRefreshing = _state.value.isRefreshing,
                        error = _state.value.error
                    )
                }
        }
    }

    fun onPlus(productId: Int, currentQty: Int) {
        setQty(productId, currentQty + 1)
    }

    fun onMinus(productId: Int, currentQty: Int) {
        setQty(productId, (currentQty - 1).coerceAtLeast(0))
    }

    private fun setQty(productId: Int, qty: Int) {
        viewModelScope.launch {
            runCatching { changeQuantity(productId, qty) }
                .onFailure { t -> _state.update { it.copy(error = t.message ?: "Update error") } }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true, error = null) }
            runCatching { refreshCart() }
                .onFailure { t -> _state.update { it.copy(error = t.message ?: "Refresh error") } }
            _state.update { it.copy(isRefreshing = false) }
        }
    }
}