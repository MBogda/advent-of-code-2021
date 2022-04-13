package advent18

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class Advent18Test {

    private val inputFileAndSum = mapOf(
        "input1" to "[[[[1,1],[2,2]],[3,3]],[4,4]]",
        "input2" to "[[[[3,0],[5,3]],[4,4]],[5,5]]",
        "input3" to "[[[[5,0],[7,4]],[5,5]],[6,6]]",
        "input4" to "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]",
        "input5" to "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]",
    )

    private val inputFileAndMagnitude = mapOf(
        "input5" to 4140,
    )

    @Test
    fun findSum() {
        for ((inputFile, sum) in inputFileAndSum) {
            val text = this.javaClass.getResourceAsStream(inputFile)?.bufferedReader()?.readLines()
                ?: fail("Can't read input file.")
            val actualSum = findSum(text)
            assertEquals(expected = sum, actual = actualSum.toString(), message = "Sums are different.")
        }
    }

    @Test
    fun findMagnitude() {
        for ((inputFile, magnitude) in inputFileAndMagnitude) {
            val text = this.javaClass.getResourceAsStream(inputFile)?.bufferedReader()?.readLines()
                ?: fail("Can't read input file.")
            val actualMagnitude = findMagnitude(text)
            assertEquals(expected = magnitude, actual = actualMagnitude, message = "Magnitudes are different.")
        }
    }
}
