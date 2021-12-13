class Day13(input: List<String>) {

  data class Dot(var x: Int, var y: Int)
  data class Fold(val axis: Char, val coord: Int)

  private val dots = mutableListOf<Dot>()
  private val folds = mutableListOf<Fold>()
  private var width = 0
  private var height = 0

  init {
    input.takeAll { it.contains(",") }.forEach { dot ->
      dot.splitTo(",") { x, y -> dots.add(Dot(x.toInt(), y.toInt())) }
    }
    input.takeAll { it.contains("=") }.forEach { fold ->
      fold.splitTo("=") { instr, coord -> folds.add(Fold(instr.last(), coord.toInt())) }
    }
    width = dots.maxOf { it.x } + 1
    height = dots.maxOf { it.y } + 1
  }

  override fun toString() = toString(false)
  private fun toString(betterPrint: Boolean) =
    StringBuilder().apply {
      for (y in 0 until height) {
        for (x in 0 until width) {
          append(if (dots.any { it.x == x && it.y == y })
                   (if (betterPrint) '▓' else '#')
                 else
                   (if (betterPrint) ' ' else '.'))
        }
        appendLine()
      }
    }.toString()

  fun fold(count: Int = -1) =
    folds.take(if (count == -1) folds.size else count).forEach { fold(it) }

  private fun fold(fold: Fold) =
    if (fold.axis == 'x') dots.foldX(fold.coord) else dots.foldY(fold.coord)

  private fun List<Dot>.foldX(x: Int) {
    filter { it.x > x }.forEach { dot ->
      dot.x = x - (dot.x - x)
    }
    width = x.coerceAtLeast(width - x - 1)
  }

  private fun List<Dot>.foldY(y: Int) {
    filter { it.y > y }.forEach { dot ->
      dot.y = y - (dot.y - y)
    }
    height = y.coerceAtLeast(height - y - 1)
  }

  fun part1() =
    apply { fold(1) }.toString().count { it == '#' }

  fun part2(betterPrint: Boolean = false) =
    apply { fold() }.toString(betterPrint)

}

fun main() {
  readInput("day13").let { input ->
    println("\nPART 1")
    Day13(input).apply {
      println("dots after one fold: ${part1()}")
    }
    println("\nPART 2")
    Day13(input).apply {
      println("output after folding:\n${part2(true)}")
    }
  }
}
