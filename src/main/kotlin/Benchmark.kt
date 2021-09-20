import kotlin.random.Random

const val ARRAY_SIZE = 20_000_000

fun main() {
    val arrays = generateArray() to generateArray()

    warmUp()
    println(
        "\u001b[36mCalculating The intersection of two arrays of size $ARRAY_SIZE," +
                " filled with random integers...\u001b[0m"
    )
    printMeasurementsFor {
        with(arrays.first.toHashSet() to arrays.second.toHashSet()) {
            println(first.filter { second.contains(it) })
        }
    }
}

fun generateArray() = IntArray(ARRAY_SIZE) { Random.nextInt(1, 200_000_001) }
