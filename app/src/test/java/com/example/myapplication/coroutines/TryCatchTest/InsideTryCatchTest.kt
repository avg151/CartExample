package com.example.myapplication.coroutines.TryCatchTest

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class InsideTryCatchTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    /* Выводы:
     * 1) Ошибка обернутая в try внутри launch обработается в catch и не попадет в родительский scope
     *
       start parent scope
       throw
       catch: throw
       end parent scope
     */

    @Test
    fun throwInside() = runTest(testDispatcher) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("log1: " + throwable.message)
        }

        scope.launch(
            context = exceptionHandler1
        ) {
            println("start parent scope")

            try {
                println("throw")
                throw Exception("throw")
            } catch (e: Exception) {
                println("catch: " + e.message)
            }

            println("end parent scope")
        }
    }
}