import kotlin.math.max
import kotlin.math.min

fun main() {
    // first
    val input = readInput("inputDay4")
    val ranges = input.map { it.split(",") }
    println(findNumberOfFullyContainedRanges(ranges))
    // second
    println(findNumberOfOverlappingRanges(ranges))
}

fun findNumberOfFullyContainedRanges(input: List<List<String>>): Int {
    return input.map {
        val (range, otherRange) = it
        fullyContains(range, otherRange)
    }.count { it }
}

fun findNumberOfOverlappingRanges(input: List<List<String>>): Int {
    return input.map {
        val (range, otherRange) = it
        overlap(range, otherRange)
    }.count { it }
}

fun fullyContains(range: String, otherRange: String): Boolean {
    val (lowerBound, upperBound) = range.getUpperLower()
    val (lowerBoundOther, upperBoundOther) = otherRange.getUpperLower()
    return ((lowerBound <= lowerBoundOther && upperBound >= upperBoundOther) || (lowerBound >= lowerBoundOther && upperBound <= upperBoundOther))
}

fun overlap(range: String, otherRange: String): Boolean {
    val (lowerBound, upperBound) = range.getUpperLower()
    val (lowerBoundOther, upperBoundOther) = otherRange.getUpperLower()
    return max(lowerBound, upperBound) <= min(lowerBoundOther, upperBoundOther)
}

fun String.getUpperLower(): List<Int> {
    return this.split("-").map { it.toInt() }
}

