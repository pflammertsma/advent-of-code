import kotlin.test.assertEquals
import org.junit.Test

class Day12Test {

  private val inputA =
    mutableListOf("start-A",
                  "start-b",
                  "A-c",
                  "A-b",
                  "b-d",
                  "A-end",
                  "b-end")
  private val inputB =
    mutableListOf("dc-end",
                  "HN-start",
                  "start-kj",
                  "dc-start",
                  "dc-HN",
                  "LN-dc",
                  "HN-end",
                  "kj-sa",
                  "kj-HN",
                  "kj-dc")
  private val inputC =
    mutableListOf("fs-end",
                    "he-DX",
                    "fs-he",
                    "start-DX",
                    "pj-DX",
                    "end-zg",
                    "zg-sl",
                    "zg-pj",
                    "pj-he",
                    "RW-he",
                    "fs-DX",
                    "pj-RW",
                    "zg-RW",
                    "start-pj",
                    "he-WI",
                    "zg-he",
                    "pj-fs",
                    "start-RW")

  @Test
  fun testPart1() {
    Day12().apply {
      assertEquals(10, part1(inputA, true).size)
      assertEquals(19, part1(inputB).size)
      assertEquals(226, part1(inputC).size)
    }
  }

  @Test
  fun testPart2() {
    Day12().apply {
      assertEquals(36, part2(inputA, true).size)
      assertEquals(103, part2(inputB).size)
      assertEquals(3509, part2(inputC).size)
    }
  }

}
