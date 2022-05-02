package advent19

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ScannersUnifierTest {

    // todo? move fields to companion object

    private val overlappingBeacons = 3

    // the first 3 beacons are always the same in all scanners, and the 4th is always an extra one

    // the main scanner
    private val mainScanner = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),
        )
    )

    // this scanner is moved by (10, 10, 10), but has the same direction as the main one
    private val movedScanner = Scanner(
        1, mutableListOf(
            Beacon(10, 10, 10),
            Beacon(11, 12, 13),
            Beacon(9, 11, 9),
            Beacon(100, 100, 100),
        )
    )

    private val expectedUnifiedWithMoved = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // move back by (-10, -10, -10)
            Beacon(90, 90, 90),
        )
    )

    // this scanner is turned as (-z, -y, -x), but it's in the same point as the main one
    private val turnedScanner = Scanner(
        2, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(-3, -2, -1),
            Beacon(1, -1, 1),
            Beacon(100, 100, 100),
        )
    )

    private val expectedUnifiedWithTurned = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // turn back by (-z, -y, -x)
            Beacon(-100, -100, -100),
        )
    )

    // this scanner is moved by (-10, -10, -10) and then turned as (-y, z, -x)
    private val movedAndTurnedScanner = Scanner(
        0, mutableListOf(
            // after the moving coordinates are:
//            Beacon(-10, -10, -10),
//            Beacon(-9, -8, -7),
//            Beacon(-11, -9, -11),

            // after the turning coordinates are:
            Beacon(10, -10, 10),
            Beacon(8, -7, 9),
            Beacon(9, -11, 11),
            Beacon(100, 100, 100),
        )
    )

    private val expectedUnifiedWithMovedAndTurned = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // turn back by (-z, -x, y)
//            Beacon(-100, -100, 100),
            // then move back by (10, 10, 10)
            Beacon(-90, -90, 110),
        )
    )

    private val expectedUnifiedAll = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            Beacon(90, 90, 90),
            Beacon(-100, -100, -100),
            Beacon(-90, -90, 110),
        )
    )

    @Test
    fun unifyScanners_movedScanner() {
        val actualScanner = mainScanner.copy()
        ScannersUnifier.unifyScanners(overlappingBeacons, actualScanner, movedScanner)
        assertEquals(expected = expectedUnifiedWithMoved, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScanners_turnedScanner() {
        val actualScanner = mainScanner.copy()
        ScannersUnifier.unifyScanners(overlappingBeacons, actualScanner, turnedScanner)
        assertEquals(expected = expectedUnifiedWithTurned, actual = actualScanner, "Unified scanners are different.")
    }

    @Test
    fun unifyScanners_movedAndTurnedScanner() {
        val actualScanner = mainScanner.copy()
        ScannersUnifier.unifyScanners(overlappingBeacons, actualScanner, movedAndTurnedScanner)
        assertEquals(
            expected = expectedUnifiedWithMovedAndTurned,
            actual = actualScanner,
            "Unified scanners are different."
        )
    }

    @Test
    fun unifyScanners_unifyAll() {
        val actualScanner = mainScanner.copy()
        ScannersUnifier.unifyScanners(
            overlappingBeacons,
            actualScanner,
            movedScanner,
            turnedScanner,
            movedAndTurnedScanner
        )
        assertEquals(expected = expectedUnifiedAll, actual = actualScanner, "Unified scanners are different.")
    }
}
