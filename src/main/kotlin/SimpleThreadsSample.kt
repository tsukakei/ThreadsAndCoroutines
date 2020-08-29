import kotlin.concurrent.thread

/**
 * Threadを用いたシンプルな並列実行
 */
fun main(args: Array<String>) {
    val t1 = thread {
        for (i in 1..100) {
            println("**T1: $i**")
        }
    }

    val t2 = thread(start = false) {
        for (i in 1..100) {
            println("{{T2: $i}}")
        }
    }
    t2.start()
}