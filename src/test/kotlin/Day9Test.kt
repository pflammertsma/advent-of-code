import kotlin.test.assertEquals
import org.junit.Test

class Day9Test {

  private val input =
    mutableListOf("2199943210",
                  "3987894921",
                  "9856789892",
                  "8767896789",
                  "9899965678")

  @Test
  fun testPart1() {
    Day9().apply {
      assertEquals(15, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day9().apply {
      assertEquals(1134, part2(input))
    }
  }

}
