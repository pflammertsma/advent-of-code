import kotlin.test.assertEquals
import org.junit.Test

class Day5Test {

  private val input =
    mutableListOf("0,9 -> 5,9",
                  "8,0 -> 0,8",
                  "9,4 -> 3,4",
                  "2,2 -> 2,1",
                  "7,0 -> 7,4",
                  "6,4 -> 2,0",
                  "0,9 -> 2,9",
                  "3,4 -> 1,4",
                  "0,0 -> 8,8",
                  "5,5 -> 8,2")

  @Test
  fun testPart1() {
    Day5().apply {
      assertEquals(5, part1(input, true))
    }
  }

  @Test
  fun testPart2() {
    Day5().apply {
      assertEquals(12, part2(input, true))
    }
  }

}
