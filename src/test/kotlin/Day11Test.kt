import kotlin.test.assertEquals
import org.junit.Test

class Day11Test {

  private val input =
    mutableListOf("5483143223",
                  "2745854711",
                  "5264556173",
                  "6141336146",
                  "6357385478",
                  "4167524645",
                  "2176841721",
                  "6882881134",
                  "4846848554",
                  "5283751526")

  private val inputEx =
    mutableListOf("11111",
                  "19991",
                  "19191",
                  "19991",
                  "11111")
  private val outputEx = "45654\n" +
    "51115\n" +
    "61116\n" +
    "51115\n" +
    "45654\n"

  @Test
  fun testPart1() {
    Day11().apply {
      assertEquals(outputEx, Day11.Matrix(inputEx).apply {
        step()
        step()
        println(toString())
      }.toString())
      assertEquals(1656, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day11().apply {
      assertEquals(195, part2(input))
    }
  }

}
