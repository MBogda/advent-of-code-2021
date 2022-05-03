package advent19

import kotlin.test.Test
import kotlin.test.assertEquals

internal class TurningVectorTest {

    companion object {
        private const val trivialVector = "(x,y,z)"
        private const val secondVector = "(-z,x,-y)"
        private val beacon = Beacon(2, 5, -3)
        private val secondTurnedBeacon = Beacon(3, 2, -5)
    }

    @Test
    fun fromString_trivialVector() {
        val actualVector = TurningVector.fromString(trivialVector)
        assertEquals(expected = 1, actual = actualVector.x, "Internal representation is different")
        assertEquals(expected = 2, actual = actualVector.y, "Internal representation is different")
        assertEquals(expected = 3, actual = actualVector.z, "Internal representation is different")
        assertEquals(expected = trivialVector, actualVector.toString(), "String representation is different.")
    }

    @Test
    fun fromString_secondVector() {
        val actualVector = TurningVector.fromString(secondVector)
        assertEquals(expected = -3, actual = actualVector.x, "Internal representation is different")
        assertEquals(expected = 1, actual = actualVector.y, "Internal representation is different")
        assertEquals(expected = -2, actual = actualVector.z, "Internal representation is different")
        assertEquals(expected = secondVector, actualVector.toString(), "String representation is different.")
    }

    @Test
    fun turn_trivialVector() {
        val actualBeacon = TurningVector.fromString(trivialVector).turn(beacon)
        assertEquals(expected = beacon, actual = actualBeacon, "Actual beacon is different.")
    }

    @Test
    fun turn_secondVector() {
        val actualBeacon = TurningVector.fromString(secondVector).turn(beacon)
        assertEquals(expected = secondTurnedBeacon, actual = actualBeacon, "Actual beacon is different.")
    }
}
