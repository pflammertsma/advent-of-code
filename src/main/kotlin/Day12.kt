import java.util.Locale

class Day12 {

  fun part1(input: List<String>, verbose: Boolean = false): MutableList<List<String>> {
    return navigate(input, 1, verbose)
  }

  fun part2(input: List<String>, verbose: Boolean = false): MutableList<List<String>> {
    return navigate(input, 2, verbose)
  }

  data class Node(val name: String, val nodes: MutableList<Node> = mutableListOf()) {
    override fun toString() = name
  }

  private fun navigate(
    input: List<String>,
    maxVisits: Int,
    verbose: Boolean,
  ): MutableList<List<String>> {
    return createNodes(input).let { nodes ->
      if (verbose) println("all nodes: $nodes")
      val route = mutableListOf<String>()
      val from = nodes.first { it.name == "start" }
      val to = from.nodes
      val validRoutes = MutableList(0) { listOf<String>() }
      navigate(route, from, to, validRoutes, maxVisits, verbose)
      if (verbose) println(validRoutes)
      validRoutes
    }
  }

  private fun createNodes(input: List<String>): MutableList<Node> =
    mutableListOf<Node>().also { nodes ->
      input.forEach { line ->
        line.splitTo("-") { a, b ->
          val nodeA = nodes.find { it.name == a } ?: Node(a).also { nodes.add(it) }
          val nodeB = nodes.find { it.name == b } ?: Node(b).also { nodes.add(it) }
          nodeA.nodes.add(nodeB)
          nodeB.nodes.add(nodeA)
        }
      }
      nodes.sortBy { it.name.lowercase(Locale.getDefault()) }
    }

  private fun navigate(
    route: MutableList<String>,
    from: Node,
    to: MutableList<Node>,
    validRoutes: MutableList<List<String>>,
    remainingMax: Int,
    verbose: Boolean,
  ): Boolean {
    route.add(from.name)
    if (from.name == "end") {
      validRoutes.add(route.toList())
      if (verbose) println("solution for $route")
      return true
    }
    to.forEach { node ->
      var nextMax = remainingMax
      if (node.name != "start" && (node.name.capitalized() || route.count { it == node.name }.also {
          nextMax = if (it == 1) 1 else nextMax
        } < remainingMax)) {
        if (verbose) println("  navigate: ${from.name} -> ${node.name} (max=$nextMax)")
        navigate(route, node, node.nodes, validRoutes, nextMax, verbose)
        route.removeLast()
      }
    }
    if (verbose) println("no paths for $route")
    return false
  }

}

fun main() {
  readInput("day12").let { input ->
    println("\nPART 1")
    Day12().apply {
      println("total routes with small caves: ${part1(input).count { it.containsSmall() }}")
    }
    println("\nPART 2")
    Day12().apply {
      println("total routes: ${part2(input).count()}")
    }
  }
}

private fun List<String>.containsSmall() =
  any { it != "start" && it != "end" && !it.capitalized() }
