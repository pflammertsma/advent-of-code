import kotlin.math.pow

class Day14(input: List<String>) {

  data class Rule(val left: Char, val right: Char, val output: Char) {
    fun apply(c1: Char, c2: Char): Char? {
      if (left == c1 && right == c2) {
        return output
      }
      return null
    }
  }

  private var polymer = input.first()
  private val rules = mutableListOf<Rule>()
  private val lookup = hashMapOf<Long, MutableMap<Char, Long>>()

  init {
    input.filter { it.contains(" -> ") }.forEach {
      it.splitTo(" -> ") { i, o ->
        rules.add(Rule(i[0], i[1], o[0]))
      }
    }
  }

  private fun recursive(steps: Int, verbose: Boolean = false): MutableMap<Char, Long> {
    System.nanoTime().let { startNanos ->
      val counts = mutableMapOf<Char, Long>()
      polymer.windowed(2, 1, partialWindows = true).forEach { pair ->
        if (!verbose) print("${pair[0]}...")
        if (pair.length < 2) {
          // Trailing char
          counts.increment(pair[0])
          if (verbose) println(pair[0])
        } else {
          step(pair[0], pair[1], steps - 1, verbose).also {
            counts.combine(it)
          }
        }
      }
      if (!verbose) println()
      println("(in ${(System.nanoTime() - startNanos) / 1000000.0}ms)")
      return counts
    }
  }

  private fun step(c1: Char, c2: Char, stepsLeft: Int, verbose: Boolean): MutableMap<Char, Long> {
    val hash = hashCode(c1, c2, stepsLeft)
    return lookup[hash] ?: mutableMapOf<Char, Long>().also { counts ->
      rules.forEach { rule ->
        rule.apply(c1, c2)?.let { c ->
          // Keep recursing
          if (stepsLeft == 0) {
            counts.increment(c1)
            counts.increment(c)
            if (verbose) {
              print(c1); print(c)
            }
          } else {
            counts.combine(step(c1, c, stepsLeft - 1, verbose))
            counts.combine(step(c, c2, stepsLeft - 1, verbose))
          }
        }
      }
      lookup[hash]?.let { cached ->
        if (counts != cached) throw IllegalStateException("collision for $c1$c2@$stepsLeft; hash is $hash")
      }
      lookup[hash] = counts
    }
  }

  fun compute(steps: Int): Long = recursive(steps).difference()

}

// Hash function to generate a unique ID for a node
fun hashCode(a: Char, b: Char, stepsLeft: Int): Long {
  return a.code.toLong() * (2.0.pow((stepsLeft + 10).toDouble()).toLong()) +
    b.code.toLong() * 2.0.pow((stepsLeft + 5).toDouble()).toLong()
}

private fun <K> MutableMap<K, Long>.difference(): Long = maxOf { it.value } - minOf { it.value }
private fun <K> MutableMap<K, Long>.increment(c: K, amount: Long = 1L) =
  set(c, (get(c) ?: 0) + amount)

private fun <K> MutableMap<K, Long>.combine(other: MutableMap<K, Long>) {
  other.forEach { increment(it.key, it.value) }
}

fun main() {
  readInput("day14").let { input ->
    println("\nPART 1")
    Day14(input).apply {
      println(compute(10))
    }
    println("\nPART 2")
    Day14(input).apply {
      println(compute(40))
    }
  }
}
