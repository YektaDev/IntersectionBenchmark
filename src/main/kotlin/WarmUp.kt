import kotlin.random.Random

fun warmUp() = println("\u001b[33mWarming up the JVM...\u001b[0m").also {
    repeat(1_000) {
        IntArray(8_000) { Random.nextInt() }.filter { it % 2 == 0 }.toHashSet().contains(1)
        repeat(10_000) {
            print("")
        }
    }
}
