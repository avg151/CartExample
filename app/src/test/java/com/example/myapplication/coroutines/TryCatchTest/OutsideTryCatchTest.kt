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

class OutsideTryCatchTest {

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
 * 1) Ошибка обернутая в try снаружи launch обработается в родительском CoroutineExceptionHandler
 * и не попадет в catch
 *
    start parent scope
    throw
    log1: throw
 */

    @Test
    fun throwInside() = runTest(testDispatcher) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("log1: " + throwable.message)
        }
        try {
            scope.launch(
                context = exceptionHandler1
            ) {
                println("start parent scope")

                println("throw")
                throw Exception("throw")

                println("end parent scope")
            }

        } catch (e: Exception) {
            println("catch: " + e.message)
        }
    }
}