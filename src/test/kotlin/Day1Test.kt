import kotlin.test.assertEquals
import org.junit.Test

class Day1Test {

  private val input =
    mutableListOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

  @Test
  fun testPart1() {
    assertEquals(7, Day1().increments(input))
  }

  @Test
  fun testPart2() {
    assertEquals(5, Day1().windowedIncrements(input))
  }

}
