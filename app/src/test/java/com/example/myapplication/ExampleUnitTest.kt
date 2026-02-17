package com.example.myapplication

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
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
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
    fun testViewModel2() = runTest(testDispatcher) {
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