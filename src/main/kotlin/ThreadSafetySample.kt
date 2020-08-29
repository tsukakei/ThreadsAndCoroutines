import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

fun main(args: Array<String>) {
    var counter = 0
    val latch = CountDownLatch(100_000)
    val t1 = thread {
        synchronized(latch) {
            for (i in 1..50_000) {
                counter++
                latch.countDown()
            }
        }
    }
    val t2 = thread {
        synchronized(latch) {
            for (i in 1..50_000) {
                counter++
                latch.countDown()
            }
        }
    }
    latch.await()
    // counter != 100_000
    println("Counter: $counter")
}