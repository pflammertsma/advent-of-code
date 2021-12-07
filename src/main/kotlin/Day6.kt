class Day6 {

  fun advanceAges(input: String, days: Int, print: Boolean = false): Long {
    if (print) println("Initial state:  $input")
    var ages = Array<Long>(9) { 0 }
    input.split(",").forEach {
      ages[it.toInt()]++
    }
    if (print) println("Grouped by age: ${ages.contentToString()}")
    for (day in 1..days) {
      val newAges = Array<Long>(9) { 0 }
      newAges[6] = ages[0]
      newAges[8] = ages[0]
      for (i in 1..8) {
        newAges[i - 1] += ages[i]
      }
      ages = newAges
      if (print) println("After ${day.toString().padStart(3, ' ')} days: ${ages.contentToString()}")
    }
    return ages.sum()
  }

}

fun main() {
  readInput("day6").let { input ->
    println("\nPART 1")
    Day6().apply {
      println("number of fish after 80 days: ${advanceAges(input[0], 80)}")
    }
    println("\nPART 2")
    Day6().apply {
      println("number of fish after 256 days: ${advanceAges(input[0], 256)}")
    }
  }
}
