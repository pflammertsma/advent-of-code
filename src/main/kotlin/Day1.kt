#!/usr/bin/env kotlin

import java.io.File

class Day1(private val input: MutableList<Int>) {

  fun increments(): Int {
    return input.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
  }

  fun windowedIncrements(): Int {
    return input.windowed(3).map { it.sum() }.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()
  }

}

fun main() {
  val input = mutableListOf<Int>().also { list ->
    File("resources/day1.txt").forEachLine {
      list.add(it.toInt())
    }
  }
  Day1(input).apply {
    println("total immediate increments: ${increments()}")
    println("total windowed increments: ${windowedIncrements()}")
  }
}
