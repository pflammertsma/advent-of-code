import kotlin.test.assertEquals
import org.junit.Test

class Day2Test {

  private val input =
    mutableListOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

  @Test
  fun testPart1() {
    Day2().apply {
      traverse(input)
      assertEquals(150, x * depth)
    }
  }

  @Test
  fun testPart2() {
    Day2().apply {
      aimedTraverse(input)
      assertEquals(900, x * depth)
    }
  }

}
