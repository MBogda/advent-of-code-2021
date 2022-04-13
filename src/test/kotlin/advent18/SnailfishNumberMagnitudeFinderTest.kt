package advent18

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnailfishNumberMagnitudeFinderTest {

    private val numberAndMagnitude = mapOf(
        "[9,1]" to 29,
        "[1,9]" to 21,
        "[[9,1],[1,9]]" to 129,
        "[[1,2],[[3,4],5]]" to 143,
        "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]" to 1384,
        "[[[[1,1],[2,2]],[3,3]],[4,4]]" to 445,
        "[[[[3,0],[5,3]],[4,4]],[5,5]]" to 791,
        "[[[[5,0],[7,4]],[5,5]],[6,6]]" to 1137,
        "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]" to 3488,
    )

    @Test
    fun findMagnitude() {
        for ((number, magnitude) in numberAndMagnitude) {
            val snailfishNumber = SnailfishNumberReader(number).read()
            val actualMagnitude = SnailfishNumberMagnitudeFinder.findMagnitude(snailfishNumber)
            assertEquals(expected = magnitude, actual = actualMagnitude, message = "Magnitudes are different.")
        }
    }
}
