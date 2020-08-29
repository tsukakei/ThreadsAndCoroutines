package threads

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val counter = AtomicInteger()
    try {
        for (i in 0..100_000) {
            thread {
                counter.incrementAndGet()
                Thread.sleep(1000)
            }
        }
    } catch (oome: OutOfMemoryError) {
        println("Spawned ${counter.get()} threads before crashing")
        exitProcess(-42)
    }
}