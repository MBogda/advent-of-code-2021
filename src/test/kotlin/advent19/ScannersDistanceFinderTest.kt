package advent19

import advent19.ScannersTestData.expectedMaxDistanceAll
import advent19.ScannersTestData.expectedMaxDistanceMoved
import advent19.ScannersTestData.expectedMaxDistanceMovedAndTurned
import advent19.ScannersTestData.expectedMaxDistanceTurned
import advent19.ScannersTestData.mainScanner
import advent19.ScannersTestData.movedAndTurnedScanner
import advent19.ScannersTestData.movedScanner
import advent19.ScannersTestData.turnedScanner
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ScannersDistanceFinderTest {

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
    fun findScannersMaxDistance_movedScanner() {
        val actualDistance = ScannersDistanceFinder.findScannersMaxDistance(mainScanner, movedScanner)
        assertEquals(expected = expectedMaxDistanceMoved, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findScannersMaxDistance_turnedScanner() {
        val actualDistance = ScannersDistanceFinder.findScannersMaxDistance(mainScanner, turnedScanner)
        assertEquals(expected = expectedMaxDistanceTurned, actual = actualDistance, "Max distance is different.")
    }

    @Test
    fun findScannersMaxDistance_movedAndTurnedScanner() {
        val actualDistance = ScannersDistanceFinder.findScannersMaxDistance(mainScanner, movedAndTurnedScanner)
        assertEquals(
            expected = expectedMaxDistanceMovedAndTurned, actual = actualDistance, "Max distance is different."
        )
    }

    @Test
    fun findScannersMaxDistance_allScanners() {
        val actualDistance = ScannersDistanceFinder.findScannersMaxDistance(
            mainScanner, movedScanner, turnedScanner, movedAndTurnedScanner
        )
        assertEquals(expected = expectedMaxDistanceAll, actual = actualDistance, "Max distance is different.")
    }
}
