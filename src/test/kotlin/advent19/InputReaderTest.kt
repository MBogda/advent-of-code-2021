package advent19

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

internal class InputReaderTest {

    companion object {
        private const val INPUT_FILE = "inputReader"
        private val expectedResult = listOf(
            Scanner(
                0, mutableListOf(
                    Beacon(1, 0, -1),
                    Beacon(100, 1000, 10000),
                    Beacon(-7, 100, -20),
                    Beacon(0, 0, 0),
                )
            ),
            Scanner(
                20, mutableListOf(
                    Beacon(-10, 0, 1)
                )
            ),
            Scanner(5, mutableListOf()),
        )
    }

    @Test
    fun readScannersData() {
        val inputText: List<String> = this.javaClass.getResourceAsStream(INPUT_FILE)?.bufferedReader()?.readLines()
            ?: fail("Can't read input file.")
        val actualResult = InputReader.readScannersData(inputText)
        assertEquals(expected = expectedResult, actual = actualResult, "Lists with scanners are different.")
    }
}
