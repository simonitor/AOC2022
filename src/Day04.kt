fun main() {
    // first
    val input = readInput("inputDay4")
    println(findNumberOfFullyContainedSections(input))

}


fun findNumberOfFullyContainedSections(input: List<String>): Int{
    return input.map { it.split(",") }.map {
        val (range, otherRange) = it
        fullyContains(range, otherRange)
    }.count { it }
}

fun fullyContains(range :String, otherRange: String): Boolean{
    val (lowerBound, upperBound) = range.getUpperLower()
    val (lowerBoundOther, upperBoundOther) = otherRange.getUpperLower()
    return ((lowerBound <= lowerBoundOther && upperBound >= upperBoundOther) || (lowerBound >= lowerBoundOther && upperBound <= upperBoundOther))
}

fun String.getUpperLower() : List<Int>{
    return this.split("-").map { it.toInt() }
}

