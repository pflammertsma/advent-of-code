sealed class Day2 {

  var depth = 0
  var x = 0

  open fun traverse(input: List<String>) {
    input.forEachAction { action, amount ->
      when (action) {
        "forward" -> goForward(amount)
        "down" -> goDown(amount)
        "up" -> goUp(amount)
        else -> throw IllegalArgumentException("Unknown action '$action'")
      }
      if (depth < 0) throw IllegalStateException("Above water")
    }
  }

  abstract fun goForward(amount: Int)
  abstract fun goDown(amount: Int)
  abstract fun goUp(amount: Int)

  class V1 : Day2() {

    override fun goForward(amount: Int) {
      x += amount
    }

    override fun goDown(amount: Int) {
      depth += amount
    }

    override fun goUp(amount: Int) {
      depth -= amount
    }

  }

  class V2 : Day2() {

    var aim = 0

    override fun goForward(amount: Int) {
      x += amount
      depth += aim * amount
    }

    override fun goDown(amount: Int) {
      aim += amount
    }

    override fun goUp(amount: Int) {
      aim -= amount
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
    println("\nPART 1")
    Day2.V1().apply {
      traverse(input)
      println("part1: horizontal position: $x; depth: $depth; product: ${x * depth}")
    }
    println("\nPART 2")
    Day2.V2().apply {
      traverse(input)
      println("part2: horizontal position: $x; depth: $depth; product: ${x * depth}")
    }
  }
}
