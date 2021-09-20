import kotlin.random.Random

const val ARRAY_SIZE = 2_000_000

fun main() {
    val arrays = generateArray() to generateArray()

    warmUp()
    println(
        "\u001b[36mCalculating The intersection of two arrays of size $ARRAY_SIZE," +
                " filled with random integers in: [1,20000000]...\u001b[0m"
    )
    printMeasurementsFor {
        with(arrays.first.toHashSet() to arrays.second.toHashSet()) {
            println(first.filter { second.contains(it) })
        }
    }
}

fun generateArray() = IntArray(ARRAY_SIZE) { Random.nextInt(1, 20_000_001) }
