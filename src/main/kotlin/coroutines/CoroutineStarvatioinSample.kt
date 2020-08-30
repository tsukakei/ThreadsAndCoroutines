package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


val latch = CountDownLatch(2 * 10)
fun main(args: Array<String>) {
    for (i in 1..10) {
        longCoroutine(i)
    }
    for (i in 1..10) {
        shortCoroutine(i)
    }
    latch.await(10, TimeUnit.SECONDS)
}

fun longCoroutine(index: Int) = GlobalScope.async {
    var uuid = UUID.randomUUID()
    for (i in 1..10_000) {
        val newUuid = UUID.randomUUID()
        if (newUuid < uuid) {
            uuid = newUuid
        }
    }
    println("Done longCoroutine: $index")
    latch.countDown()
}

fun shortCoroutine(index: Int) = GlobalScope.async {
    println("Done shortCoroutine: $index")
    latch.countDown()
}
