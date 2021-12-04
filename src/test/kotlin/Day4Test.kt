import kotlin.test.assertEquals
import org.junit.Test

class Day4Test {

  private val input =
    readInput("day4test")

  @Test
  fun testPart1() {
    Day4().apply {
      assertEquals(4512, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day4().apply {
      assertEquals(1924, part2(input))
    }
  }

}
