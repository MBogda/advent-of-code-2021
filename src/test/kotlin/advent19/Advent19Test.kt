package advent19

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

internal class Advent19Test {
    companion object {
        private const val INPUT_FILE = "input"
        private const val EXPECTED_BEACONS = 79
        private const val EXPECTED_DISTANCE = 3621
    }

    @Test
    fun findAllBeacons() {
        val text = this.javaClass.getResourceAsStream(INPUT_FILE)?.bufferedReader()?.readLines()
            ?: fail("Can't read input file.")
        val actualBeaconsNumber = findAllBeacons(text)
        assertEquals(expected = EXPECTED_BEACONS, actual = actualBeaconsNumber, "Number of beacons is different.")
    }

    @Test
    fun findLargestDistance() {
        val text = this.javaClass.getResourceAsStream(INPUT_FILE)?.bufferedReader()?.readLines()
            ?: fail("Can't read input file.")
        val actualDistance = findLargestDistance(text)
        assertEquals(expected = EXPECTED_DISTANCE, actual = actualDistance, "Largest distance is different.")
    }
}
