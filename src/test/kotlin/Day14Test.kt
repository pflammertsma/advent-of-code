import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.junit.Test

class Day14Test {

  private val input =
    mutableListOf("NNCB",
                  "",
                  "CH -> B",
                  "HH -> N",
                  "CB -> H",
                  "NH -> C",
                  "HB -> C",
                  "HC -> B",
                  "HN -> C",
                  "NN -> C",
                  "BH -> H",
                  "NC -> B",
                  "NB -> B",
                  "BN -> B",
                  "BB -> N",
                  "BC -> B",
                  "CC -> N",
                  "CN -> C")

  @Test
  fun testUniqueHashCodes() {
    val hashes = mutableMapOf<Long, String>()
    for (a in 'A'..'Z')
      for (b in 'A'..'Z')
        for (i in 0..40) {
          hashCode(a, b, i).let { hash ->
            if (hashes.contains(hash)) {
              println("hash conflict: $a,$b,$i == ${hashes[hash]}")
            }
            assertNull(hashes[hash])
            hashes.put(hash, "$a,$b,$i")
          }
        }
  }

  @Test
  fun testPart1() {
    Day14(input).apply {
      assertEquals(18L, compute(4))
      assertEquals(1588L, compute(10))
    }
  }

  @Test
  fun testPart2() {
    Day14(input).apply {
      assertEquals(2188189693529L, compute(40))
    }
  }

}
