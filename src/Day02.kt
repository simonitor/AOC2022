import GameResult.*

fun main() {
    // first
    val games = readInput("inputDay2").map { it.split(" ") }.map {
        Pair(Move.translateMove(it[1]), Move.translateMove(it[0]))
    }
    println(calculatePoints(games))

    // second
    val games2 = readInput("inputDay2").map { it.split(" ") }.map {
        val enemyMove = Move.translateMove(it[0])
        Pair(Move.translateMoveSecondRuleSet(enemyMove, it[1]), enemyMove)
    }
    println(calculatePoints(games2))
}

fun calculatePoints(games: List<Pair<Move, Move>>): Int {
    return games.sumOf { winMatrix[it.first.index][it.second.index].points + it.first.points }
}

val winMatrix = arrayOf(
    //******rock****paper***cisors*******
    arrayOf(DRA,    LOS,    WIN), // rock
    arrayOf(WIN,    DRA,    LOS), // paper
    arrayOf(LOS,    WIN,    DRA) // cisors
)

enum class GameResult(val points: Int) {
    WIN(6),
    DRA(3),
    LOS(0);

    companion object {
        fun fromMove(encryptedMove: String): GameResult {
            return when (encryptedMove) {
                "X" -> LOS
                "Y" -> DRA
                "Z" -> WIN
                else -> throw IllegalArgumentException()
            }
        }
    }
}

enum class Move(val index: Int, val points: Int) {
    ROCK(0, 1),
    PAPER(1, 2),
    CISORS(2, 3);

    companion object {
        fun translateMove(encryptedMove: String): Move {
            return when (encryptedMove) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> CISORS
                else -> throw IllegalArgumentException()
            }
        }

        fun translateMoveSecondRuleSet(enemyMove: Move, encryptedMove: String): Move {
            return when (GameResult.fromMove(encryptedMove)) {
                LOS -> Move.values()[(winMatrix[enemyMove.index].indexOf(WIN))]
                DRA -> Move.values()[(winMatrix[enemyMove.index].indexOf(DRA))]
                WIN -> Move.values()[winMatrix[enemyMove.index].indexOf(LOS)]
            }
        }
    }
}


