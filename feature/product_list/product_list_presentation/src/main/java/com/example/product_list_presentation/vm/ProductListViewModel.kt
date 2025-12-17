package com.example.product_list_presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.product_list_domain.usecase.GetProductListUseCase
import com.example.product_list_presentation.ui.ui_state.ProductListUiState
import com.example.product_list_presentation.ui.mapper.dataState
import com.example.product_list_presentation.ui.mapper.errorState
import com.example.product_list_presentation.ui.mapper.loadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductList: GetProductListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ProductListUiState())
    val state: StateFlow<ProductListUiState> = _state.asStateFlow()

    init {
        load()
    }

    private fun load() {
        _state.value = loadingState()

        viewModelScope.launch {
            runCatching {
                getProductList()
            }.onSuccess { list ->
                _state.value = dataState(list)
            }.onFailure { e ->
                _state.value = errorState(e)
            }
        }
    }
}