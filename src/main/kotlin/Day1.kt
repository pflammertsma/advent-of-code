class Day1 {

  fun increments(input: List<Int>): Int {
    return input.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
  }

  fun windowedIncrements(input: List<Int>): Int {
    return input.windowed(3).map { it.sum() }.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
  }

}

fun main() {
  readInput("day1").map { it.toInt() }.let { input ->
    Day1().apply {
      println("total immediate increments: ${increments(input)}")
    }
    Day1().apply {
      println("total windowed increments: ${windowedIncrements(input)}")
    }
  }
}
