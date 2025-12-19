package com.example.cart.remote.error

class CartException(val failure: CartFailure) : RuntimeException(failure.toString())