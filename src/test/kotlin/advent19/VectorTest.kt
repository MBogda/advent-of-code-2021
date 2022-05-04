package advent19

import kotlin.test.Test
import kotlin.test.assertEquals

internal class VectorTest {

    companion object {
        private val beacon1 = Beacon(2, -4, 8)
        private val beacon2 = Beacon(13, 7, -1)

        private val trivialVector = Vector(0, 0, 0)
        private val oneToTwoVector = Vector(11, 11, -9)
        private val twoToOneVector = Vector(-11, -11, 9)

        private val secondVector = Vector(2, 5, -1)
        private val secondVectorBeacon1 = Beacon(4, 1, 7)
    }

    @Test
    fun findVector_sameBeacon() {
        val actualVector = beacon1.findVector(beacon1)
        assertEquals(expected = trivialVector, actual = actualVector, "Actual vector is different.")
    }

    @Test
    fun findVector_oneToTwo() {
        val actualVector = beacon1.findVector(beacon2)
        assertEquals(expected = oneToTwoVector, actual = actualVector, "Actual vector is different.")
    }

    @Test
    fun findVector_twoToOne() {
        val actualVector = beacon2.findVector(beacon1)
        assertEquals(expected = twoToOneVector, actual = actualVector, "Actual vector is different.")
    }

    @Test
    fun move_trivialVector() {
        val actualBeacon = trivialVector.move(beacon1)
        assertEquals(expected = beacon1, actual = actualBeacon, "Actual beacon is different.")
    }

    @Test
    fun move_secondVector() {
        val actualBeacon = secondVector.move(beacon1)
        assertEquals(expected = secondVectorBeacon1, actual = actualBeacon, "Actual beacon is different.")
    }

    @Test
    fun manhattanDistance_trivialVector() {
        val actualDistance = trivialVector.manhattanDistance()
        assertEquals(expected = 0, actual = actualDistance, "Manhattan distance is different.")
    }

    @Test
    fun manhattanDistance_secondVector() {
        val actualDistance = secondVector.manhattanDistance()
        assertEquals(expected = 8, actual = actualDistance, "Manhattan distance is different.")
    }
}
