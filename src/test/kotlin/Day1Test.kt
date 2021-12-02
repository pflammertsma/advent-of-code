import kotlin.test.assertEquals
import org.junit.Test

class Day1Test {

  private val obj = Day1(
    mutableListOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
  )

  @Test
  fun testPart1() {
    assertEquals(7, obj.increments())
  }

  @Test
  fun testPart2() {
    assertEquals(5, obj.windowedIncrements())
  }

}
