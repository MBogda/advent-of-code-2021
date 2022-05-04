package advent19

import advent19.ScannersTestData.OVERLAPPING_BEACONS
import advent19.ScannersTestData.expectedScannerCoordinatesAll
import advent19.ScannersTestData.expectedUnifiedAll
import advent19.ScannersTestData.expectedUnifiedWithMoved
import advent19.ScannersTestData.expectedUnifiedWithMovedAndTurned
import advent19.ScannersTestData.expectedUnifiedWithTurned
import advent19.ScannersTestData.mainScanner
import advent19.ScannersTestData.movedAndTurnedScanner
import advent19.ScannersTestData.movedScanner
import advent19.ScannersTestData.turnedScanner
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlin.test.*

internal class ScannersUnifierTest {

    @BeforeTest
    fun beforeTest() {
        mockkObject(PropertyStorage)
        every { PropertyStorage.getOverlappingBeacons() } returns OVERLAPPING_BEACONS
    }

    @AfterTest
    fun afterTest() {
        unmockkAll()
    }

    @Test
    fun unifyScanners_noScanners() {
        assertFailsWith<IllegalArgumentException>("There are no scanners, but they were unified.") {
            ScannersUnifier.unifyScanners()
        }
    }

    @Test
    fun unifyScanners_mainScannerOnly() {
        val actualScanner = ScannersUnifier.unifyScanners(mainScanner)
        assertEquals(expected = mainScanner, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScanners_movedScanner() {
        val actualScanner = ScannersUnifier.unifyScanners(mainScanner, movedScanner)
        assertEquals(expected = expectedUnifiedWithMoved, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScanners_turnedScanner() {
        val actualScanner = ScannersUnifier.unifyScanners(mainScanner, turnedScanner)
        assertEquals(expected = expectedUnifiedWithTurned, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScanners_movedAndTurnedScanner() {
        val actualScanner = ScannersUnifier.unifyScanners(mainScanner, movedAndTurnedScanner)
        assertEquals(
            expected = expectedUnifiedWithMovedAndTurned, actual = actualScanner, "Unified scanners are different."
        )
    }

    @Test
    fun unifyScanners_unifyAll() {
        val actualScanner = ScannersUnifier.unifyScanners(
            mainScanner, movedScanner, turnedScanner, movedAndTurnedScanner
        )
        assertEquals(expected = expectedUnifiedAll, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScannersAndGetCoordinates_noScanners() {
        assertFailsWith<IllegalArgumentException>("Coordinates were found while no scanners.") {
            ScannersUnifier.unifyScannersAndGetCoordinates()
        }
    }

    @Test
    fun unifyScannersAndGetCoordinates_mainScanner() {
        val actualCoordinates = ScannersUnifier.unifyScannersAndGetCoordinates(mainScanner)
        val expectedCoordinates = mutableListOf(Vector(0, 0, 0))
        assertEquals(expected = expectedCoordinates, actual = actualCoordinates, "Scanners' coordinates are different.")
    }

    @Test
    fun unifyScannersAndGetCoordinates_allScanners() {
        val actualCoordinates = ScannersUnifier.unifyScannersAndGetCoordinates(
            mainScanner, movedScanner, turnedScanner, movedAndTurnedScanner
        )
        assertEquals(
            expected = expectedScannerCoordinatesAll, actual = actualCoordinates, "Scanners' coordinates are different."
        )
    }
}
