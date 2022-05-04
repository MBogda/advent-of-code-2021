package advent19

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame

internal class ScannerTest {

    companion object {
        private val beacon1 = Beacon(2, -4, 8)
        private val beacon2 = Beacon(13, 7, -1)

        private val scanner = Scanner(0, mutableListOf(beacon1, beacon2))
    }

    @Test
    fun copy_noParams() {
        val copiedScanner = scanner.copy()
        assertEquals(expected = scanner, actual = copiedScanner, "Copied scanner's values are different.")
        assertNotSame(
            illegal = scanner.beacons,
            actual = copiedScanner.beacons,
            "Copied scanner has the same beacons instance (while shouldn't)."
        )
    }

    @Test
    fun testCopy_paramNumber() {
        val copiedScanner = scanner.copy(number = 10)
        assertNotEquals(
            illegal = scanner,
            actual = copiedScanner,
            "Copied scanner's values are equal (while shouldn't)."
        )
        assertNotEquals(
            illegal = scanner.number,
            actual = copiedScanner.number,
            "Copied scanner's numbers are equal (while shouldn't)."
        )
        assertEquals(
            expected = scanner.beacons,
            actual = copiedScanner.beacons,
            "Copied scanner's beacons are different."
        )
        assertNotSame(
            illegal = scanner.beacons,
            actual = copiedScanner.beacons,
            "Copied scanner has the same beacons instance (while shouldn't)."
        )
    }

    @Test
    fun testCopy_paramBeacons() {
        val copiedScanner = scanner.copy(beacons = mutableListOf(beacon1))
        assertNotEquals(
            illegal = scanner,
            actual = copiedScanner,
            "Copied scanner's values are equal (while shouldn't)."
        )
        assertNotEquals(
            illegal = scanner.beacons,
            actual = copiedScanner.beacons,
            "Copied scanner's beacons are equal (while shouldn't)."
        )
        assertEquals(
            expected = scanner.number,
            actual = copiedScanner.number,
            "Copied scanner's numbers are different."
        )
    }

    @Test
    fun testCopy_paramsNumberAndBeacons() {
        val copiedScanner = scanner.copy(number = 10, beacons = mutableListOf(beacon1))
        assertNotEquals(
            illegal = scanner,
            actual = copiedScanner,
            "Copied scanner's values are equal (while shouldn't)."
        )
        assertNotEquals(
            illegal = scanner.number,
            actual = copiedScanner.number,
            "Copied scanner's numbers are equal (while shouldn't)."
        )
        assertNotEquals(
            illegal = scanner.beacons,
            actual = copiedScanner.beacons,
            "Copied scanner's beacons are equal (while shouldn't)."
        )
    }
}
