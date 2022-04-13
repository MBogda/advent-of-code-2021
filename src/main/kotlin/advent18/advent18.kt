package advent18

fun main() {
    val inputText = object {}.javaClass.getResourceAsStream("input")?.bufferedReader()?.readLines()
        ?: throw AssertionError("Can't read input file.")
    val magnitude = findMagnitude(inputText)
    println(magnitude)  // 3793
}

fun findSum(inputText: List<String>): SnailfishNumber {
    var resultNumber: SnailfishNumber? = null
    for (line in inputText) {
        val addedNumber = SnailfishNumberReader(line).read()
        if (resultNumber == null) {
            resultNumber = addedNumber
        } else {
            resultNumber += addedNumber
        }
    }
    return resultNumber ?: SnailfishNumber(0)
}

fun findMagnitude(inputFile: List<String>): Int {
    val resultNumber = findSum(inputFile)
    return SnailfishNumberMagnitudeFinder.findMagnitude(resultNumber)
}
