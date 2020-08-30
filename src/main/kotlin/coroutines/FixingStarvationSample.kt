package coroutines

import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit


fun main(args: Array<String>) {
    for (i in 1..10) {
        fixedLongCoroutine(i)
    }
    for (i in 1..10) {
        shortCoroutine(i)
    }
    latch.await(10, TimeUnit.SECONDS)
}

fun fixedLongCoroutine(index: Int) = GlobalScope.async {
    var uuid = UUID.randomUUID()
    for (i in 1..10_000) {
        val newUuid = UUID.randomUUID()
        if (newUuid < uuid) {
            uuid = newUuid
        }
        if (i % 100 == 0) {
            yield()
        }
    }
    println("Done fixedLongCoroutine: $index")
    latch.countDown()
}