fun main() {
    val input = readFile("inputDay6")
    // first
    println(findIndexOfByDecodeType(input, DecodeType.PACKAGE))
    // second
    println(findIndexOfByDecodeType(input, DecodeType.MESSAGE))
}

fun findIndexOfByDecodeType(message: String, type: DecodeType): Int {
    message.windowed(type.value).forEachIndexed { index, s ->
        if (s.toSet().size == type.value)
            return index + type.value
    }
    error("No msg or package found")
}

enum class DecodeType(val value: Int){
    PACKAGE(4), MESSAGE(14)
}