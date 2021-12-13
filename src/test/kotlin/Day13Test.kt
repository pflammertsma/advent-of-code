import kotlin.test.assertEquals
import org.junit.Test

class Day13Test {

  private val input =
    mutableListOf("6,10",
                  "0,14",
                  "9,10",
                  "0,3",
                  "10,4",
                  "4,11",
                  "6,0",
                  "6,12",
                  "4,1",
                  "0,13",
                  "10,12",
                  "3,4",
                  "3,0",
                  "8,4",
                  "1,10",
                  "2,14",
                  "8,10",
                  "9,0",
                  "",
                  "fold along y=7",
                  "fold along x=5")
  private val output = "#####\n" +
    "#...#\n" +
    "#...#\n" +
    "#...#\n" +
    "#####\n" +
    ".....\n" +
    ".....\n"

  @Test
  fun testPart1() {
    Day13(input).apply {
      assertEquals(17, part1())
    }
  }

  @Test
  fun testPart2() {
    Day13(input).apply {
      assertEquals(output, part2())
    }
  }

}
