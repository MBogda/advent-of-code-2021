package advent18

import kotlin.math.max

fun main() {
    val inputText = object {}.javaClass.getResourceAsStream("input")?.bufferedReader()?.readLines()
        ?: throw AssertionError("Can't read input file.")
    val magnitude = findMagnitude(inputText)
    println("Part one: $magnitude")  // 3793

    val largestMagnitude = findLargestMagnitudePair(inputText)
    println("Part two: $largestMagnitude")  // 4695
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

fun findMagnitude(inputStrings: List<String>): Int {
    val resultNumber = findSum(inputStrings)
    return SnailfishNumberMagnitudeFinder.findMagnitude(resultNumber)
}

fun findLargestMagnitudePair(inputStrings: List<String>): Int {
    var largestMagnitude = 0
    for (line1 in inputStrings) {
        for (line2 in inputStrings) {
            val number1 = SnailfishNumberReader(line1).read()
            val number2 = SnailfishNumberReader(line2).read()
            val magnitude = SnailfishNumberMagnitudeFinder.findMagnitude(number1 + number2)
            largestMagnitude = max(largestMagnitude, magnitude)
        }
    }
    return largestMagnitude
}
