import java.io.File
import kotlin.math.absoluteValue

fun readInput(filename: String) = File("resources/$filename.txt").readLines()

fun List<String>.toInts() = first().split(",").map { it.toInt() }

fun Char.toNumber() =
  this - '0'

fun String.fromBinary(): Int =
  Integer.parseInt(this, 2)

fun Int.toBinary(length: Int) =
  Integer.toBinaryString(this).padStart(length, '0')

fun Int.invert(length: Int) =
  Integer.parseInt(List(length) { '1' }
                     .joinToString(""), 2).let {
    this.xor(it)
  }

fun <E> MutableList<E>.replaceIf(predicate: (E) -> E) {
  for (i in 0 until size) {
    set(i, predicate(get(i)))
  }
}

fun String.splitTo(s: String, predicate: (left: String, right: String) -> Unit) =
  split(s).pair(predicate)

fun <E> List<E>.pair(predicate: (left: E, right: E) -> Unit) =
  predicate(this[0], this[1])

val Int.divergentSeries: Int
  get() = (this * (this + 1)) / 2

fun String.sort() =
  StringBuilder().also { sb -> chars().sorted().forEach { sb.append(it.toChar()) } }.toString()

fun <E> Array<Array<E>>.print() = forEach { row ->
  row.forEach { value ->
    print(value)
  }
  println()
}

fun Collection<Int>.product(): Int {
  var product = 1
  forEach {
    product *= it
  }
  return product
}

fun String.capitalized() = all { c -> c in 'A'..'Z' }
