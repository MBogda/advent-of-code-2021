package advent19

import advent19.ScannersTestData.OVERLAPPING_BEACONS
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
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

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
}
