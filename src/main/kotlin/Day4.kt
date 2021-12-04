class Day4 {

  class Game(input: List<String>) {

    private val numbers: MutableList<Int>
    private val boards = mutableListOf<Board>()

    private var calledNumber = 0
    private var winningBoard: Board? = null

    init {
      input.toMutableList().apply {
        numbers = removeFirst().split(",").map { it.toInt() }.toMutableList()
        var id = 0
        while (size > 5) {
          removeFirst()
          val list = mutableListOf<MutableList<Int?>>()
          for (i in 0 until 5) {
            removeFirst().let { line ->
              list.add(
                line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()
              )
            }
          }
          boards.add(Board(id++, list))
        }
      }
    }

    data class Board(
      val id: Int,
      val tiles: List<MutableList<Int?>>,
    ) {

      fun mark(number: Int) {
        tiles.forEach { row ->
          row.replaceIf { if (it == number) null else it }
        }
      }

      fun countUnmarked() = tiles.sumOf { it.filterNotNull().sum() }

      fun won(): Boolean {
        // Check columns
        for (col in 0 until 5) {
          var noneRemaining = true
          for (row in 0 until 5) {
            if (tiles[row][col] != null) noneRemaining = false
          }
          if (noneRemaining) {
            println("Col $col complete for board #$id")
            return true
          }
        }
        // Check rows
        for (row in 0 until 5) {
          if (tiles[row].count { it == null } == 5) {
            println("Row $row complete for board #$id")
            return true
          }
        }
        return false
      }
    }

    fun getScore(board: Board?) = (board?.countUnmarked() ?: 0) * calledNumber

    private fun callNumber(): Boolean {
      calledNumber = numbers.removeFirstOrNull() ?: return false
      val it = boards.iterator()
      while (it.hasNext()) {
        val board = it.next()
        board.mark(calledNumber)
        if (board.won()) {
          winningBoard = board
          it.remove()
        }
      }
      return true
    }

    fun playGame(): Board? {
      while (winningBoard == null) {
        if (!callNumber()) {
          throw IllegalStateException("No winning boards")
        }
      }
      return winningBoard
    }

    fun playGameUntilEnd(): Board? {
      while (true) {
        winningBoard = null
        playGame()
        if (boards.isEmpty()) return winningBoard
      }
    }

  }

  fun part1(input: List<String>) =
    Game(input).run { playGame()?.let { getScore(it) } }

  fun part2(input: List<String>) =
    Game(input).run { playGameUntilEnd()?.let { getScore(it) } }

}

fun main() {
  readInput("day4").let { input ->
    println("\nPART 1")
    Day4().apply {
      println("final score of first winning board: ${part1(input)}")
    }
    println("\nPART 2")
    Day4().apply {
      part2(input)
      println("final score of last winning board: ${part2(input)}")
    }
  }
}
