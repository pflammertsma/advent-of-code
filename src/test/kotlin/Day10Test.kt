import kotlin.test.assertEquals
import org.junit.Test

class Day10Test {

  private val input =
    mutableListOf("[({(<(())[]>[[{[]{<()<>>",
                    "[(()[<>])]({[<{<<[]>>(",
                    "{([(<{}[<>[]}>{[]{[(<()>",
                    "(((({<>}<{<{<>}{[]{[]{}",
                    "[[<[([]))<([[{}[[()]]]",
                    "[{[{({}]{}}([{[{{{}}([]",
                    "{<[[]]>}<{[{[{[]{()[[[]",
                    "[<(<(<(<{}))><([]([]()",
                    "<{([([[(<>()){}]>(<<{{",
                    "<{([{{}}[<[[[<>{}]]]>[]]")

  @Test
  fun testPart1() {
    Day10().apply {
      assertEquals(26397, part1(input))
    }
  }

  @Test
  fun testPart2() {
    Day10().apply {
      assertEquals(288957L, part2(input))
    }
  }

}
