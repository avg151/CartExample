package com.example.myapplication.coroutines.TryCatchTest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class MixedTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun throwInside() = runTest(testDispatcher) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        println("1")

        scope.launch {
            println("2")

            try {
                println("3")

                scope.launch {
                    println("4")
                    delay(1000)
                    println("5")
                    throw RuntimeException("Ex")
                    println("6")
                }

                println("7")

                scope.launch {
                    println("8")

                    try {
                        println("9")
                        delay(2000)
                        println("10")
                    } finally {
                        println("11")
                    }

                    println("12")
                }

                println("13")

            } catch (e: Exception) {
                println("14")
            }

            println("15")
        }

        println("16")
    }
}