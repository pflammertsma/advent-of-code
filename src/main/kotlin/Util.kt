import java.io.File

fun readInput(filename: String) = File("resources/$filename.txt").readLines()

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
