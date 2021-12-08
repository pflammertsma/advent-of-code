class Day8 {

  fun part1(input: List<String>): Int {
    var count = 0
    input.forEach {
      it.splitTo(" | ") { _, output ->
        output.split(" ").forEach { digit ->
          when (digit.length) {
            2 -> {              // digit 1
              count++
            }
            3 -> {              // digit 7
              count++
            }
            4 -> {              // digit 4
              count++
            }
            7 -> {              // digit 8
              count++
            }
          }
        }
      }
    }
    return count
  }

  fun part2(input: List<String>): Int {
    var sum = 0
    input.forEach { line ->
      line.splitTo(" | ") { digits, output ->
        println(output)
        sum += StringBuilder().also { sb ->
          decode(digits).let { decoded ->
            output.split(" ").map {
              it.sort()
            }.forEach { digit ->
              decoded.indexOf(digit).let {
                sb.append(it)
              }
            }
          }
        }.toString().toInt()
      }
    }
    return sum
  }

  private fun union(a: String?, b: String?) =
    when {
      a == null -> ""
      b == null -> a
      else -> StringBuilder().also { sb ->
        val ac = a.toCharArray()
        val bc = b.toCharArray()
        var i = 0
        var j = 0
        var last = ' '
        while (true) {
          if (ac.size <= i && bc.size <= j) break
          if (ac.size > i && (bc.size <= j || ac[i] < bc[j])) {
            ac[i].let { if (it != last) sb.append(it); last = it }
            i++
          } else {
            bc[j].let { if (it != last) sb.append(it); last = it }
            j++
          }
        }
      }.toString()
    }

  private fun diff(a: String?, b: String?) =
    when {
      a == null -> ""
      b == null -> a
      else -> StringBuilder().also { sb ->
        a.chars().forEach { c ->
          if (!b.chars().anyMatch { c == it })
            sb.append(c.toChar())
        }
      }.toString()
    }

  fun decode(digits: String): Array<String?> {
    val known = arrayOfNulls<String>(10)
    val patterns = digits.split(" ").map { it.sort() }.toMutableList()

    known[1] = patterns.removeFirst { it.length == 2 }
    known[4] = patterns.removeFirst { it.length == 4 }
    known[7] = patterns.removeFirst { it.length == 3 }
    known[8] = patterns.removeFirst { it.length == 7 }

    // subtract 1 from 7, remaining segment is the top
    val top = diff(known[7], known[1]).first()

    // union of 4 and 7, to the bottom segment
    val btm = union(known[4], known[7]).let { union ->
      (patterns.matches {
        diff(it, union).let { diff ->
          if (diff.length == 1) diff else null
        }
      }?.first() ?: ' ').also {
        // Recombining with the bottom segment to find 9
        union(union, "$it").let { segments9 ->
          known[9] = patterns.removeFirst {
            it == segments9
          }
        }
      }
    }

    // subtract 1 and the top and bottom segments to find 3 and the middle segment
    var mid = ' '
    union(known[1], (if (top < btm) "$top$btm" else "$btm$top")).let { union ->
      known[3] = patterns.removeFirst {
        diff(it, union).let { diff ->
          if (diff.length == 1) {
            mid = diff.first()
            true
          } else false
        }
      }
    }

    // find the 5 by removing all segments for 9
    known[5] = patterns.removeFirst {
      diff(it, known[9]).isEmpty()
    }

    // remove the middle segment from the 8 to find the zero
    diff(known[8], "$mid").let { segments8 ->
      known[0] = patterns.removeFirst {
        it == segments8
      }
    }

    // find the 6 by removing 3
    known[6] = patterns.removeFirst {
      diff(it, known[3]).length == 2
    }

    // last one remaining is the 2
    known[2] = patterns.removeFirst { true }

    println("decoded: " + known.contentToString())
    return known
  }

}

fun <E> MutableList<E>.matches(predicate: (E) -> E?): E? {
  for (element in this) predicate(element).let {
    if (it != null) return it
  }
  return null
}

fun <E> MutableList<E>.removeFirst(predicate: (E) -> Boolean): E? =
  removeAt(indexOfFirst { predicate(it) })

fun main() {
  readInput("day8").let { input ->
    println("\nPART 1")
    Day8().apply {
      println(part1(input))
    }
    println("\nPART 2")
    Day8().apply {
      println(part2(input))
    }
  }
}
