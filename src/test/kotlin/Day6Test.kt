import kotlin.test.assertEquals
import org.junit.Test

class Day6Test {

  private val input = "3,4,3,1,2"

  @Test
  fun testPart1() {
    Day6().apply {
      assertEquals(26, advanceAges(input, 18, print = true))
      assertEquals(5934, advanceAges(input, 80))
    }
  }

  @Test
  fun testPart2() {
    Day6().apply {
      assertEquals(26984457539, advanceAges(input, 256))
    }
  }

}
