package advent18

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SnailfishNumberSplitterTest {

    private val splitter = SnailfishNumberSplitter()

    private val numbersToSplit = mapOf(
        "[10,11]" to "[[5,5],11]",
        "[3,11]" to "[3,[5,6]]",
        "[101,0]" to "[[50,51],0]",
        "[[[[9,10],11],12],13]" to "[[[[9,[5,5]],11],12],13]"
    )

    @Test
    fun split() {
        for ((beforeSplit, afterSplit) in numbersToSplit) {
            val snailfishNumber = SnailfishNumberReader(beforeSplit).read()
            splitter.split(snailfishNumber)
            assertEquals(
                expected = afterSplit,
                actual = snailfishNumber.toString(),
                message = "Split numbers are different"
            )
        }
    }
}