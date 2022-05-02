package advent19

fun main() {
    val inputText: List<String> = object {}.javaClass.getResourceAsStream("input")?.bufferedReader()?.readLines()
        ?: throw AssertionError("Can't read input file.")
}
