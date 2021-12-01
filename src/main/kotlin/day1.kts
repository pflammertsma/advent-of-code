#!/usr/bin/env kotlin

// Run this script with "test" in the options to evaluate the test case

import java.io.File

val testMode = args.contains("test")

val input =
  if (testMode) {
    // Test case
    mutableListOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
  } else {
    // From input
    mutableListOf<Int>().also { list ->
      File("../resources/input.txt").forEachLine {
        list.add(it.toInt())
      }
    }
  }

input.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum().let { sum ->
  println("total immediate increments: $sum")
}

input.windowed(3).map { it.sum() }.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum().let { sum ->
  println("total windowed increments: $sum")
}
