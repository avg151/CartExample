package com.example.myapplication.coroutines.CoroutineExceptionHandler

import kotlinx.coroutines.CoroutineExceptionHandler
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

class ChildCoroutineExceptionHandlerTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    /*
        Вывод:
         1)CoroutineExceptionHandler: Отрабатывает только родительский, в дочерних ошибка не ловится
         2)При падении в родителе, дочерние отменяются, не запускаются в независимости от SupervisorJob
    */


    @Test
    fun throwInParent() = runTest(testDispatcher) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("log0: " + throwable.message)
        }

        val exceptionHandler2 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("log1: " + throwable.message)
        }

        val exceptionHandler3 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("log2: " + throwable.message)
        }

        scope.launch(
            context = exceptionHandler1
        ) {
            println("start parent scope")

            launch(context = exceptionHandler2) {
                println("start child 1")

                delay(500)

                println("fail in child 1")
                throw Exception("fail in child 1")

                println("end child 1")
            }

            launch(context = exceptionHandler3) {
                println("start child 2")
                delay(1000)
                println("end child 2")
            }

            println("end parent scope")
        }.join()

        /*
            start parent scope
            log1: fail in parent
         */
    }




}