import kotlin.math.absoluteValue

class Day7 {

  fun part1(input: List<String>): Int {
    return shortestDistance(input.toInts()) { a, b ->
      (a - b).absoluteValue
    }

  }

  fun part2(input: List<String>): Int {
    return shortestDistance(input.toInts()) { a, b ->
      (a - b).absoluteValue.divergentSeries
    }
  }

  private fun shortestDistance(positions: List<Int>, distanceFunction: (Int, Int) -> Int): Int {
    val max = positions.maxOrNull() ?: 0
    var bestValue = Integer.MAX_VALUE
    for (i in 0..max) {
      positions.sumOf { distanceFunction(i, it) }.let {
        if (bestValue > it) bestValue = it
      }
    }
    return bestValue
  }

}

fun main() {
  readInput("day7").let { input ->
    println("\nPART 1")
    Day7().apply {
      println(part1(input))
    }
    println("\nPART 2")
    Day7().apply {
      println(part2(input))
    }
  }
}
