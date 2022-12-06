fun main() {
    val input = readFile("inputDay6")
    // first
    println(findIndexOfPackage(input, DecodeType.PACKAGE))
    // second
    println(findIndexOfPackage(input, DecodeType.MESSAGE))
}

fun findIndexOfPackage(message: String, type: DecodeType): Int {
    message.windowed(type.value).forEachIndexed { index, s ->
        if (s.toSet().size == type.value)
            return index + type.value
    }
    error("No msg found or package found")
}

enum class DecodeType(val value: Int){
    PACKAGE(4), MESSAGE(14)
}