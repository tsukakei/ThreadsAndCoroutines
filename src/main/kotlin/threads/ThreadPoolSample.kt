package threads

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

fun main(args: Array<String>) {
    // Try setting this to 1, number of cores, 100, 2000, 3000, and see what happens
    val pool = Executors.newFixedThreadPool(4)
    val counter = AtomicInteger(0)
    val start = System.currentTimeMillis()
    for (i in 1..10_000) {
        pool.submit {
            counter.incrementAndGet()
            Thread.sleep(100)
            counter.incrementAndGet()
        }
    }
    pool.awaitTermination(10, TimeUnit.SECONDS)
    pool.shutdown()
    println("Took me ${System.currentTimeMillis() - start} millis to complete ${counter.get() / 2} tasks")
}