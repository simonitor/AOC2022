fun main() {
    val elfs = readFile("inputDay1").split("\n\n").map { Elf(it) }
    val answer = getFattestElf(elfs)?.calories
    println("The most calories are $answer")
}

fun getFattestElf(elfs: List<Elf>) = elfs.maxByOrNull { it.calories }

class Elf(private val cal: String) {
    val calories = calculateCalories()
    fun calculateCalories(): Int {
        return cal.split("\n").sumOf { it.toInt() }
    }
}
