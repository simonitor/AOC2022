fun main() {
    // first
    val input = readInput("inputDay3")
    println(findEquals(input))
    // second
    println(findBadge(input))

}

fun findEquals(rucksacks: List<String>): Int {
    return rucksacks.map {
        it.substring(0, (it.length / 2)) to it.substring(it.length / 2, it.length)
    }.sumOf {
        normalizeCharValue(it.first.toSet().intersect(it.second.toSet()).random())
    }
}

fun findBadge(rucksacks: List<String>): Int {
    return rucksacks.windowed(3, 3).sumOf {
        normalizeCharValue(it[0].toSet().intersect(it[1].toSet().intersect(it[2].toSet())).random())
    }
}

fun normalizeCharValue(c: Char): Int {
    c.run { return if (isUpperCase()) (code % 64 + 26) else code % 96 }
}