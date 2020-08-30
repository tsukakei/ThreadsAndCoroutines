package coroutines

import kotlinx.coroutines.*

fun main(args: Array<String>) {
    val j = GlobalScope.launch {
        for (i in 1..10_000) {
            if (i % 1000 == 0) {
                println(i)
                yield()
            }
        }
    }
    runBlocking {
        j.join()
    }
}
