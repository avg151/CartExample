package com.example.myapplication.coroutines.Ordering

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class OrderingCoroutineTest {

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
    fun testViewModel1() = runTest(testDispatcher) {
        println("1")

        launch {
            println("2")
        }

        /*
            1
            2
     */
    }

    @Test
    fun testViewModel2() = runTest(testDispatcher) {
        launch {
            println("1")
        }

        println("2")

        /*
            2
            1
     */
    }

    @Test
    fun testViewModel3() = runTest(testDispatcher) {
        println("1")

        launch {
            println("2")
        }

        launch {
            println("3")
        }

        println("4")
        delay(100)
        println("5")

        /*
            1
            4
            2
            3
            5
     */
    }

    @Test
    fun testViewModel4() = runTest(testDispatcher) {
        println("1")

        launch {
            println("2")
            delay(200)
            println("3")
        }

        coroutineScope {
            println("enter coroutineScope")
            launch {
                println("A")
                delay(500)
                println("B")
            }

            println("C")
            delay(100)
            println("exit coroutineScope")
        }

        launch {
            println("4")
            delay(500)
            println("5")
        }

        println("6")
        delay(300)

        println("7")

        delay(100)
        println("8")

        /*
        1
        enter coroutineScope
        C
        2
        A
        exit coroutineScope
        3
        B
        6
        4
        7
        8
        5
         */
    }

    @Test
    fun testViewModel5() = runTest(testDispatcher) {
        println("1")

        val scope = CoroutineScope(Job() + Dispatchers.Default)

        println("2")

        scope.launch {
            println("3")

            launch {
                println("A")
            }

            println("4")
            coroutineScope {
                println("5")
                launch {
                    println("6")
                    delay(500)
                    println("B")
                }

                println("7")
                delay(100)
                println("C")
            }

            println("D")
        }.join()
        /*
            1
            2
            3
            4
            5
            A
            7
            6
            C
            B
            D
         */
    }
}