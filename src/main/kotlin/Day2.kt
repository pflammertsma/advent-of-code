#!/usr/bin/env kotlin

import java.io.File

class Day2(private val input: MutableList<String>) {

  var depth = 0
  var x = 0
  var aim = 0

  private fun reset() {
    depth = 0
    x = 0
    aim = 0
  }

  fun traverse() {
    reset()
    input.forEach {
      it.split(' ').zipWithNext { action, distance ->
        when (action) {
          "forward" -> x += distance.toInt()
          "down" -> depth += distance.toInt()
          "up" -> depth -= distance.toInt()
          else -> throw IllegalArgumentException("Unknown action '$action'")
        }
      }
    }
  }

  fun aimedTraverse() {
    reset()
    input.forEach {
      it.split(' ').zipWithNext { action, amount ->
        when (action) {
          "forward" -> {
            x += amount.toInt()
            depth += aim * amount.toInt()
          }
          "down" -> {
            aim += amount.toInt()
          }
          "up" -> {
            aim -= amount.toInt()
          }
          else -> {
            throw IllegalArgumentException("Unknown action '$action'")
          }
        }
      }
    }
  }
}

fun main() {
  val list = mutableListOf<String>().also { list ->
    File("resources/day2.txt").forEachLine {
      list.add(it)
    }
  }
  Day2(list).apply {
    traverse()
    println("part1: horizontal position: $x; depth: $depth; product: ${x * depth}")

    aimedTraverse()
    println("part2: horizontal position: $x; depth: $depth; product: ${x * depth}")
  }
}
