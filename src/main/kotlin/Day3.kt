class Day3 {

  private fun mostCommonAt(list: List<String>, i: Int) =
    list.sumOf { it[i].toNumber() }.let {
      if (it * 2 >= list.size) 1
      else 0
    }

  private fun mostCommonBits(input: List<String>): String {
    return StringBuilder().apply {
      val length = input.firstOrNull()?.length ?: 0
      for (i in 0 until length) {
        append(mostCommonAt(input, i))
      }
    }.toString()
  }

  fun powerConsumption(input: List<String>): Int {
    val mostCommonBits = mostCommonBits(input)
    val gamma = mostCommonBits.fromBinary().also { gamma ->
      println("gamma: $mostCommonBits ($gamma)")
    }
    // Invert the binary value
    val epsilon = gamma.invert(mostCommonBits.length).also {
      println("epsilon: ${it.toBinary(mostCommonBits.length)} ($it)")
    }
    return gamma * epsilon
  }

  private fun findByBitFrequency(
    input: List<String>,
    length: Int,
    mostCommon: Boolean,
  ): List<String> {
    return input.toMutableList().also { list ->
      for (i in 0 until length) {
        val winner = mostCommonAt(list, i).let {
          // Invert for least common
          if (mostCommon) it else 1 - it
        }
        // Reduce result set
        list.removeIf { it[i].toNumber() != winner }
        if (list.isEmpty()) throw IllegalStateException("No matches for $winner")
        if (list.size == 1) break
      }
      if (list.size != 1) throw IllegalStateException("Expected exactly one result")
    }
  }

  fun lifeSupportRating(input: List<String>): Int {
    val length = input.firstOrNull()?.length ?: 0
    val oxygenRating = findByBitFrequency(input, length, true).first()
    val co2Rating = findByBitFrequency(input, length, false).first()
    println("oxygenRating: $oxygenRating (${oxygenRating.fromBinary()})")
    println("co2Rating: $co2Rating (${co2Rating.fromBinary()})")
    return oxygenRating.fromBinary() * co2Rating.fromBinary()
  }

}

fun main() {
  readInput("day3").let { input ->
    println("\nPART 1")
    Day3().apply {
      println("powerConsumption: ${powerConsumption(input)}")
    }
    println("\nPART 2")
    Day3().apply {
      println("lifeSupportRating: ${lifeSupportRating(input)}")
    }
  }
}
