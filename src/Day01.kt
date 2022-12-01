fun main() {
    //part1
    val elfs = readFile("inputDay1").split("\n\n").map { Elf(it) }
    val answer = getFattestElf(elfs).calories
    println("The most calories are $answer")

    //part2
    val answerPart2 = getTopThreeElfs(elfs).sumOf { it.calories }
    println("The most calories are $answerPart2")
}

fun getFattestElf(elfs: List<Elf>) = elfs.maxBy { it.calories }

class Elf(private val cal: String) {
    val calories = calculateCalories()
    fun calculateCalories(): Int {
        return cal.split("\n").sumOf { it.toInt() }
    }
}

fun getTopThreeElfs(elfs: List<Elf>): List<Elf>{
    val topThree = emptyList<Elf>().toMutableList()
    val elfsMutable = elfs.toMutableList()
    repeat(3){
        val elf = getFattestElf(elfsMutable)
        topThree.add(elf)
        elfsMutable.remove(elf)
    }
    return topThree.toList()
}
