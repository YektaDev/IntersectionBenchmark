import java.lang.Runtime.getRuntime
import java.lang.System.getProperty
import kotlin.system.measureTimeMillis

fun report(title: Any, value: Any) = println("$title: \u001B[32m$value\u001b[0m")

inline fun printMeasurementsFor(block: () -> Unit) {
    val runtime = getRuntime()
    val usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory()
    val time = measureTimeMillis { block() }
    val usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory()

    println()
    report("Execution time", "${time / 1000f} sec")
    report("Used memory", "~${(usedMemoryAfter - usedMemoryBefore) / 1000} MB")
    report("Java version", Runtime.version())
    report("OS name", getProperty("os.name"))
    report("OS version", getProperty("os.version"))
    report("OS architecture", getProperty("os.arch"))
}
