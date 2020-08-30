package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger


fun main(args: Array<String>) {
    val latch = CountDownLatch(10_000)
    val counter = AtomicInteger()

    val start = System.currentTimeMillis()
    for (i in 1..10_000) {
        GlobalScope.launch {
            counter.incrementAndGet()
            delay(100)
            counter.incrementAndGet()
            latch.countDown()
        }
    }
    latch.await(10, TimeUnit.SECONDS)
    println("Executed ${counter.get() / 2} coroutines in ${System.currentTimeMillis() - start} millis")
}
