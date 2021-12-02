class Day2 {

  var depth = 0
  var x = 0
  var aim = 0

  fun traverse(input: List<String>) {
    input.forEachAction { action, amount ->
      when (action) {
        "forward" -> x += amount
        "down" -> depth += amount
        "up" -> depth -= amount
        else -> throw IllegalArgumentException("Unknown action '$action'")
      }
      if (depth < 0) throw IllegalStateException("Above water")
    }
  }

  fun aimedTraverse(input: List<String>) {
    input.forEachAction { action, amount ->
      when (action) {
        "forward" -> {
          x += amount
          depth += aim * amount
        }
        "down" -> aim += amount
        "up" -> aim -= amount
        else -> throw IllegalArgumentException("Unknown action '$action'")
      }
      if (depth < 0) throw IllegalStateException("Above water")
    }
  }
}

private fun List<String>.forEachAction(predicate: (String, Int) -> Any) {
  forEach {
    it.split(' ').let { predicate(it[0], it[1].toInt()) }
  }
}

fun main() {
  readInput("day2").let { input ->
    Day2().apply {
      traverse(input)
      println("part1: horizontal position: $x; depth: $depth; product: ${x * depth}")
    }
    Day2().apply {
      aimedTraverse(input)
      println("part2: horizontal position: $x; depth: $depth; product: ${x * depth}")
    }
  }
}
