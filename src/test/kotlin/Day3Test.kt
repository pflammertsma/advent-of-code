import kotlin.test.assertEquals
import org.junit.Test

class Day3Test {

  private val input =
    mutableListOf("00100",
                  "11110",
                  "10110",
                  "10111",
                  "10101",
                  "01111",
                  "00111",
                  "11100",
                  "10000",
                  "11001",
                  "00010",
                  "01010")

  @Test
  fun testPart1() {
    Day3().apply {
      assertEquals(22 * 9, powerConsumption(input))
    }
  }

  @Test
  fun testPart2() {
    Day3().apply {
      assertEquals(23 * 10, lifeSupportRating(input))
    }
  }

}
