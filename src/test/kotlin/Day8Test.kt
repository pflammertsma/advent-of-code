import kotlin.test.assertEquals
import org.junit.Test

class Day8Test {

  private val input = readInput("day8test")

  @Test
  fun testPart1() {
    Day8().apply {
      assertEquals(26, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day8().apply {
      assertEquals(10, decode(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab"
      ).count { it != null })
      assertEquals(5353, part2(listOf(
        "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
      )))
      assertEquals(61229, part2(input))
    }
  }

}
