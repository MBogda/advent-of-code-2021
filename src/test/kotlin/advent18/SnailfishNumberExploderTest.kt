package advent18

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SnailfishNumberExploderTest {

    private val reader = SnailfishNumberReader()

    private val numbersToExplode = mapOf(
        "[[[[[9,8],1],2],3],4]" to "[[[[0,9],2],3],4]",
        "[[6,[5,[4,[3,2]]]],1]" to "[[6,[5,[7,0]]],3]",
        "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]" to "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]",
        "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]" to "[[3,[2,[8,0]]],[9,[5,[7,0]]]]",
    )

    @Test
    fun explode() {
        for ((strBeforeExplode, strAfterExplode) in numbersToExplode) {
            val beforeExplode = reader.readNumber(strBeforeExplode)
            SnailfishNumberExploder().explode(beforeExplode)
            assertEquals(strAfterExplode, beforeExplode.toString(), "Exploded numbers are different")
        }
    }
}
