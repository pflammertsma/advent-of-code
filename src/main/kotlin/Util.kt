import java.io.File

fun readInput(filename: String) = File("resources/$filename.txt").readLines()
