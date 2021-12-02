open class Day2 {

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

  protected open fun goForward(amount: Int) {
    x += amount
  }

  protected open fun goDown(amount: Int) {
    depth += amount
  }

  protected open fun goUp(amount: Int) {
    depth -= amount
  }

}

class Day2v2 : Day2() {

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
    Day2v2().apply {
      traverse(input)
      println("part2: horizontal position: $x; depth: $depth; product: ${x * depth}")
    }
  }
}
