package advent18

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnailfishNumberAdderTest {

    companion object {
        private val numbersToAdd = listOf(
            Triple("[1,2]", "[3,4]", "[[1,2],[3,4]]"),
            Triple("[[[[4,3],4],4],[7,[[8,4],9]]]", "[1,1]", "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"),
        )
    }

    @Test
    fun add() {
        for ((number1, number2, result) in numbersToAdd) {
            val snailfishNumber1 = SnailfishNumberReader(number1).read()
            val snailfishNumber2 = SnailfishNumberReader(number2).read()
            val snailfishNumberSum = SnailfishNumberAdder.add(snailfishNumber1, snailfishNumber2)
            assertEquals(expected = result, actual = snailfishNumberSum.toString(), message = "Sums are different.")
        }
    }
}
