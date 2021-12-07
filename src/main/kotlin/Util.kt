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
