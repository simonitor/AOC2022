fun main() {
    // first
    val (startBoard, movelist) = readFile("inputDay5").split("\n\n").map { it.split("\n") }
    val boardState = setupBoard(startBoard.toMutableList())
    val parsedMoveList = parseMoveList(movelist)
    applyMoves(boardState, parsedMoveList)
    println(getTopOfStacks(boardState))
    val boardState2 = setupBoard(startBoard.toMutableList())
    applyMovesWithNewCrane(boardState2, parsedMoveList)
    println(getTopOfStacks(boardState2))
}

fun setupBoard(startBoard: MutableList<String>): MutableMap<String, MutableList<Char>> {
    val coordinate = startBoard.removeLast()
    val boardState = emptyMap<String, MutableList<Char>>().toMutableMap()
    coordinate.filter { it != ' ' }.forEach { boardState[it.toString()] = emptyList<Char>().toMutableList() }
    for (i in startBoard.size - 1 downTo 0) {
        for (e in 0 until startBoard[i].length) {
            if (startBoard[i][e] !in " []".toSet()) {
                boardState[coordinate[e].toString()]?.add(startBoard[i][e])
            }
        }
    }
    return boardState
}

fun parseMoveList(moves :List<String>): List<List<String>>{
    return moves.map { moveInstruction ->
        moveInstruction.split(" ").filter {
            it.contains(
                Regex("[0-9]")
            )
        }
    }
}

fun getTopOfStacks(boardState: MutableMap<String, MutableList<Char>>): String {
    return buildString {
        for (i in 1..boardState.size) {
            this.append(boardState[i.toString()]?.last())
        }
    }
}

fun applyMoves(boardState: MutableMap<String, MutableList<Char>>, moveList: List<List<String>>) {
    moveList.forEach {
        val (amount, from, to) = it
        for (i in 0 until amount.toInt()) {
            val char = boardState[from]?.removeLast() ?: return
            boardState[to]?.add(char)
        }
    }
}

fun applyMovesWithNewCrane(boardState: MutableMap<String, MutableList<Char>>, moveList: List<List<String>>) {
    moveList.forEach {
        val (amount, from, to) = it
        val stack = boardState[from]?.takeLast(amount.toInt()) ?: return
        repeat(amount.toInt()) {
            boardState[from]?.removeLast() ?: return
        }
        boardState[to]?.addAll(stack)
    }
}


