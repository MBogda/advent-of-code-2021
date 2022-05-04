package advent19

import advent19.ScannersTestData.expectedMaxDistanceAll
import advent19.ScannersTestData.expectedMaxDistanceMoved
import advent19.ScannersTestData.expectedMaxDistanceMovedAndTurned
import advent19.ScannersTestData.expectedMaxDistanceTurned
import advent19.ScannersTestData.expectedScannerCoordinatesAll
import advent19.ScannersTestData.mainScanner
import advent19.ScannersTestData.movedAndTurnedScanner
import advent19.ScannersTestData.movedScanner
import advent19.ScannersTestData.turnedScanner
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlin.test.*

internal class MaxDistanceFinderTest {

    @BeforeTest
    fun beforeTest() {
        mockkObject(PropertyStorage)
        every { PropertyStorage.getOverlappingBeacons() } returns ScannersTestData.OVERLAPPING_BEACONS
    }

    @AfterTest
    fun afterTest() {
        unmockkAll()
    }

    @Test
    fun findScannersMaxDistance_noScanners() {
        assertFailsWith<IllegalArgumentException>("Max distance was found while no scanners.") {
            MaxDistanceFinder.findScannersMaxDistance()
        }
    }

    @Test
    fun findScannersMaxDistance_mainScannerOnly() {
        val actualDistance = MaxDistanceFinder.findScannersMaxDistance(mainScanner)
        assertEquals(expected = 0, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findScannersMaxDistance_movedScanner() {
        val actualDistance = MaxDistanceFinder.findScannersMaxDistance(mainScanner, movedScanner)
        assertEquals(expected = expectedMaxDistanceMoved, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findScannersMaxDistance_turnedScanner() {
        val actualDistance = MaxDistanceFinder.findScannersMaxDistance(mainScanner, turnedScanner)
        assertEquals(expected = expectedMaxDistanceTurned, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findScannersMaxDistance_movedAndTurnedScanner() {
        val actualDistance = MaxDistanceFinder.findScannersMaxDistance(mainScanner, movedAndTurnedScanner)
        assertEquals(
            expected = expectedMaxDistanceMovedAndTurned, actual = actualDistance, "Max distance is different."
        )
    }

    @Test
    fun findScannersMaxDistance_allScanners() {
        val actualDistance = MaxDistanceFinder.findScannersMaxDistance(
            mainScanner, movedScanner, turnedScanner, movedAndTurnedScanner
        )
        assertEquals(expected = expectedMaxDistanceAll, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findMaxDistance_noVectors() {
        val actualDistance = MaxDistanceFinder.findMaxDistance(emptyList())
        val expectedDistance = 0
        assertEquals(expected = expectedDistance, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findMaxDistance_oneVector() {
        val givenVectors = mutableListOf(Vector(1, 3, 10))
        val actualDistance = MaxDistanceFinder.findMaxDistance(givenVectors)
        val expectedDistance = 0
        assertEquals(expected = expectedDistance, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findMaxDistance_fourVectors() {
        val actualDistance = MaxDistanceFinder.findMaxDistance(expectedScannerCoordinatesAll)
        assertEquals(expected = expectedMaxDistanceAll, actual = actualDistance, "Max distance is different.")
    }
}
