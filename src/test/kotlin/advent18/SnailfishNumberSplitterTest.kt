package advent18

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SnailfishNumberSplitterTest {

    private val numbersToSplit = mapOf(
        "[10,11]" to "[[5,5],11]",
        "[3,11]" to "[3,[5,6]]",
        "[101,0]" to "[[50,51],0]",
        "[[[[9,10],11],12],13]" to "[[[[9,[5,5]],11],12],13]"
    )

    private val numbersNotToSplit = listOf(
        "[1,2]",
        "[[3,4],[5,6]]",
        "[[[7,8],[9,0]],1]",
    )

    @Test
    fun split_numbersToSplit() {
        for ((beforeSplit, afterSplit) in numbersToSplit) {
            val snailfishNumber = SnailfishNumberReader(beforeSplit).read()
            val result = SnailfishNumberSplitter.split(snailfishNumber)
            assertEquals(expected = true, actual = result, message = "Number wasn't split.")
            assertEquals(
                expected = afterSplit,
                actual = snailfishNumber.toString(),
                message = "Split numbers are different"
            )
        }
    }

    @Test
    fun split_numbersNotToSplit() {
        for (notToSplit in numbersNotToSplit) {
            val snailfishNumber = SnailfishNumberReader(notToSplit).read()
            val result = SnailfishNumberSplitter.split(snailfishNumber)
            assertEquals(expected = false, actual = result, message = "Number was split.")
            assertEquals(
                expected = notToSplit,
                actual = snailfishNumber.toString(),
                message = "Not split numbers are different"
            )
        }
    }
}
