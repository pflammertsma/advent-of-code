class Day5 {

  class Region(input: List<String>) {
    private val clouds = input.map { Rect(it) }
    private val w = clouds.maxOf { it.x1.coerceAtLeast(it.x2) + 1 }
    private val h = clouds.maxOf { it.y1.coerceAtLeast(it.y2) + 1 }
    private val region = List(h) { MutableList(w) { 0 } }

    fun plotSimple() {
      clouds.forEach { cloud ->
        // Horizontal or vertical lines only
        if (cloud.y1 == cloud.y2 || cloud.x1 == cloud.x2) {
          // Vertical lines
          cloud.lineTo { x, y -> region[y][x]++ }
        }
      }
    }

    fun plotFull() {
      clouds.forEach { cloud ->
        cloud.lineTo { x, y ->
          region[y][x]++
        }
      }
    }

    fun print() = region.forEachIndexed { y, row ->
      row.forEach { count ->
        print(if (count == 0) "." else count)
      }
      println()
    }

    fun countOverlaps() = region.sumOf { row -> row.count { it >= 2 } }

  }

  class Rect(input: String) {
    var x1 = 0
    var y1 = 0
    var x2 = 0
    var y2 = 0

    init {
      input.split(" -> ").pair { left, right ->
        left.splitTo(",") { x, y -> x1 = x.toInt(); y1 = y.toInt() }
        right.splitTo(",") { x, y -> x2 = x.toInt(); y2 = y.toInt() }
      }
    }

    fun lineTo(predicate: (x: Int, y: Int) -> Unit) {
      val dX = Integer.signum(x2 - x1)
      val dY = Integer.signum(y2 - y1)
      var x = x1
      var y = y1
      while (true) {
        predicate(x, y)
        if (x == x2 && y == y2) break
        x += dX; y += dY
      }
    }

    override fun toString() = "$x1,$y1 -> $x2,$y2"

  }

  fun part1(input: List<String>, print: Boolean = false): Int {
    return Region(input).run {
      plotSimple()
      if (print) print()
      countOverlaps()
    }
  }

  fun part2(input: List<String>, print: Boolean = false): Int {
    return Region(input).run {
      plotFull()
      if (print) print()
      countOverlaps()
    }
  }

}

fun main() {
  readInput("day5").let { input ->
    println("\nPART 1")
    Day5().apply {
      println("overlapping points on horizontal or vertical lines: ${part1(input)}")
    }
    println("\nPART 2")
    Day5().apply {
      println("all overlapping points on lines: ${part2(input)}")
    }
  }
}
