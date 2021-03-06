import java.lang.Runtime.getRuntime
import java.lang.System.getProperty
import kotlin.system.measureTimeMillis

fun report(title: Any, value: Any) = println("$title: \u001B[32m$value\u001b[0m")

inline fun printMeasurementsFor(title: String, printResult: Boolean, block: () -> Unit) {
    val runtime = getRuntime()
    val usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory()
    val time = measureTimeMillis {
        block().let { if (printResult) println(it) }
    }
    val usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory()

    println(title)
    report("Execution time", "${time / 1000f} sec")
    report("Used memory", "~${(usedMemoryAfter - usedMemoryBefore) / 1000} MB")
    println()
}

fun printBenchmarkDetails() {
    println("-----------------------------------------")
    report("Kotlin version", "1.5.30")
    report("JRE version", Runtime.version())
    report("OS name", getProperty("os.name"))
    report("OS version", getProperty("os.version"))
    report("OS architecture", getProperty("os.arch"))
    println("-----------------------------------------")
}
