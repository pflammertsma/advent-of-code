import kotlin.test.assertEquals
import org.junit.Test

class Day7Test {

  private val input =
    mutableListOf("16,1,2,0,4,2,7,1,2,14")

  @Test
  fun testPart1() {
    Day7().apply {
      assertEquals(37, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day7().apply {
      assertEquals(168, part2(input))
    }
  }

}
