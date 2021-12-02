import kotlin.test.assertEquals
import org.junit.Test

class Day2Test {

  private val obj = Day2(
    mutableListOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
  )

  @Test
  fun testPart1() {
    obj.traverse()
    assertEquals(150, obj.x * obj.depth)
  }

  @Test
  fun testPart2() {
    obj.aimedTraverse()
    assertEquals(900, obj.x * obj.depth)
  }

}
