class Day9 {

  class Map(input: List<String>) {

    data class POI(val x: Int, val y: Int, val depth: Int, var visited: Boolean = false)

    private var width: Int
    private var height: Int
    private var map: Array<Array<Int>>

    var minimums = mutableListOf<POI>()

    init {
      width = input.first().length
      height = input.size
      map = Array(height) { Array(width) { 0 } }
      input.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
          map[y][x] = c.toNumber()
        }
      }
      map.forEachIndexed { y, row ->
        row.forEachIndexed { x, d ->
          if ((x == 0 || d < map[y][x - 1]) &&
            (y == 0 || d < map[y - 1][x]) &&
            (x == width - 1 || d < map[y][x + 1]) &&
            (y == height - 1 || d < map[y + 1][x])
          ) {
            minimums.add(POI(x, y, d))
          }
        }
      }
    }

    fun basins(): MutableList<Int> {
      val basins = mutableListOf<Int>()
      mutableListOf<POI>().apply { addAll(minimums) }.let { minimums ->
        minimums.forEach { poi ->
          val visited = Array(height) { Array(width) { false } }
          if (!poi.visited) basins.add(navigate(minimums, poi.x, poi.y, visited))
        }
      }
      return basins
    }

    private fun navigate(
      minimums: MutableList<POI>,
      x: Int,
      y: Int,
      visited: Array<Array<Boolean>>,
    ): Int {
      var res = 0
      if (!visited[y][x] && map[y][x] < 9) {
        visited[y][x] = true
        minimums.filter { it.x == x && it.y == y }.forEach {
          it.visited = true
        }
        res++
        if (x > 0) res += navigate(minimums, x - 1, y, visited)
        if (y > 0) res += navigate(minimums, x, y - 1, visited)
        if (x < width - 1) res += navigate(minimums, x + 1, y, visited)
        if (y < height - 1) res += navigate(minimums, x, y + 1, visited)
      }
      return res
    }

  }

  fun part1(input: List<String>): Int {
    Map(input).let { map ->
      return map.minimums.sumOf { it.depth + 1 }
    }
  }

  fun part2(input: List<String>): Int {
    Map(input).let { map ->
      map.basins().let { basins ->
        return basins.apply { sortDescending() }.take(3).product()
      }
    }
  }

}

fun main() {
  readInput("day9").let { input ->
    Day9().apply {
      println("\nPART 1")
      println("sum of risk levels: ${part1(input)}")
      println("\nPART 2")
      println("product of largest basins: ${part2(input)}")
    }
  }
}
