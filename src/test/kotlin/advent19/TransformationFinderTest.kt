package advent19

import advent19.ScannersTestData.OVERLAPPING_BEACONS
import advent19.ScannersTestData.expectedVectorsMoved
import advent19.ScannersTestData.expectedVectorsMovedAndTurned
import advent19.ScannersTestData.expectedVectorsTurned
import advent19.ScannersTestData.mainScanner
import advent19.ScannersTestData.mainScannerRelativeMatrix
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

internal class TransformationFinderTest {

    companion object {
        val oneBeaconScanner = Scanner(0, mutableListOf(Beacon(1, 3, 10)))
        val oneBeaconMatrix = listOf(setOf(Beacon(0, 0, 0)))
    }

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
    fun findBeaconsMatrix_oneBeacon() {
        val actualMatrix = TransformationFinder.findBeaconsMatrix(oneBeaconScanner)
        assertEquals(expected = oneBeaconMatrix, actual = actualMatrix, "Matrix is different.")
    }

    @Test
    fun findBeaconsMatrix_mainScanner() {
        val actualMatrix = TransformationFinder.findBeaconsMatrix(mainScanner)
        assertEquals(expected = mainScannerRelativeMatrix, actual = actualMatrix, "Matrix is different.")
    }

    @Test
    fun findTransformationVectors_movedScanner() {
        val actualTransformationVectors = TransformationFinder.findTransformationVectors(mainScanner, movedScanner)
        assertEquals(
            expected = expectedVectorsMoved,
            actual = actualTransformationVectors,
            "Transformation vectors are different."
        )
    }

    @Test
    fun findTransformationVectors_turnedScanner() {
        val actualTransformationVectors = TransformationFinder.findTransformationVectors(mainScanner, turnedScanner)
        assertEquals(
            expected = expectedVectorsTurned,
            actual = actualTransformationVectors,
            "Transformation vectors are different."
        )
    }

    @Test
    fun findTransformationVectors_movedAndTurnedScanner() {
        val actualTransformationVectors =
            TransformationFinder.findTransformationVectors(mainScanner, movedAndTurnedScanner)
        assertEquals(
            expected = expectedVectorsMovedAndTurned,
            actual = actualTransformationVectors,
            "Transformation vectors are different."
        )
    }
}
