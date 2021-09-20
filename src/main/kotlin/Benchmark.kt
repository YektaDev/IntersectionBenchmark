import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

const val ARRAY_SIZE = 2_000_000

fun main() {
    val arrays = generateArray() to generateArray()

    warmUp()
    println(
        "\u001b[36mCalculating The intersection of two arrays of size $ARRAY_SIZE," +
                " filled with random integers in: [1,20000000]...\u001b[0m"
    )

    printMeasurementsFor("Using the 'GOOD' algorithm (the desired situation): ", printResult = false) {
        with(arrays.first.toHashSet() to arrays.second.toHashSet()) {
            first.filter { second.contains(it) }
        }
    }

    printMeasurementsFor("Using the 'EVEN BETTER' algorithm (with the power of coroutines): ", printResult = false) {
        val searcherSet = arrays.second.toHashSet()
        val chunks = 50
        val chunkSize = arrays.first.size / chunks

        runBlocking {
            repeat(chunks) { iteration ->
                launch(Dispatchers.Default) {
                    for (i in chunkSize * (iteration) until chunkSize * (iteration + 1)) {
                        searcherSet.contains(arrays.first[i])
                    }
                }.join()
            }
        }
    }

    printBenchmarkDetails()
}

fun generateArray() = IntArray(ARRAY_SIZE) { Random.nextInt(1, 20_000_001) }
