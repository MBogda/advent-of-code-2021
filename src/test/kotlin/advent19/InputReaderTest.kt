package advent19

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

internal class InputReaderTest {

    val inputFile = "inputReader"
    val expectedResult = listOf(
        Scanner(
            0, listOf(
                Beacon(1, 0, -1),
                Beacon(100, 1000, 10000),
                Beacon(-7, 100, -20),
                Beacon(0, 0, 0),
            )
        ),
        Scanner(
            20, listOf(
                Beacon(-10, 0, 1)
            )
        ),
        Scanner(5, emptyList()),
    )

    @Test
    fun readScannersData() {
        val inputText: List<String> = this.javaClass.getResourceAsStream(inputFile)?.bufferedReader()?.readLines()
            ?: fail("Can't read input file.")
        val actualResult = InputReader.readScannersData(inputText)
        assertEquals(expected = expectedResult, actual = actualResult, "Lists with scanners are different.")
    }
}
