package com.example.cart_di


import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FakeRemote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RealRemote