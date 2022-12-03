import GameResult.*


fun main() {
    val games = readInput("inputDay2").map { it.split(" ") }.map {
        Pair(Move.translateMove(it[1]), Move.translateMove(it[0]))
    }
    print(calculatePoints(games))

}

fun calculatePoints(games: List<Pair<Move, Move>>): Int {
    return games.sumOf { winMatrix[it.first.index][it.second.index].points + it.first.points }
}

val winMatrix = arrayOf(
    arrayOf(DRA, LOS, WIN), //rock
    arrayOf(WIN, DRA, LOS), //paper
    arrayOf(LOS, WIN, DRA) //ccisor
)

enum class GameResult(val points: Int) {
    WIN(6),
    DRA(3),
    LOS(0)
}

enum class Move(val index: Int, val points: Int) {
    ROCK(0, 1),
    PAPER(1, 2),
    CISOR(2, 3);

    companion object {
        fun translateMove(encryptedMove: String): Move {
            return when (encryptedMove) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> CISOR
                else -> throw IllegalArgumentException()
            }
        }
    }
}


