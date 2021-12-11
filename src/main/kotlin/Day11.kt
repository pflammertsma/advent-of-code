import java.lang.RuntimeException

class Day11 {

  class Matrix(input: List<String>) {
    private val width = input.first().length
    private val height = input.size
    private val map = Array(height) { Array(width) { 0 } }

    init {
      input.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
          map[y][x] = c.toNumber()
        }
      }
    }

    fun step(): Int {
      var flashes = 0
      for (x in 0 until width)
        for (y in 0 until height) {
          flashes += increment(x, y)
        }
      for (x in 0 until width)
        for (y in 0 until height)
          if (map[y][x] > 9) map[y][x] = 0
      return flashes
    }

    private fun increment(x: Int, y: Int): Int {
      var flashes = 0
      map[y][x]++
      if (map[y][x] == 10) {
        flashes++
        // Increment neighbors
        for (dY in -1..1)
          for (dX in -1..1)
            if ((dX != 0 || dY != 0) && x + dX in 0 until width && y + dY in 0 until height) {
              flashes += increment(x + dX, y + dY)
            }
      }
      return flashes
    }

    fun count() = width * height

    override fun toString(): String {
      val sb = StringBuilder()
      for (x in 0 until width) {
        for (y in 0 until height) {
          sb.append(map[y][x])
        }
        sb.appendLine()
      }
      return sb.toString()
    }

  }

  fun part1(input: List<String>): Int {
    var flashes = 0
    Matrix(input).apply {
      for (i in 1..100) flashes += step()
    }
    return flashes
  }

  fun part2(input: List<String>): Int {
    Matrix(input).apply {
      for (i in 1..1000) if (step() == count()) return i
    }
    return 0
  }

}

fun main() {
  readInput("day11").let { input ->
    println("\nPART 1")
    Day11().apply {
      println(part1(input))
    }
    println("\nPART 2")
    Day11().apply {
      println(part2(input))
    }
  }
}
