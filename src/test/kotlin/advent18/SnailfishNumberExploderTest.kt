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
        // `updateLeftmost` should be self-called
        "[[[[1,[9,9]],[[0,0],1]],1],1]" to "[[[[10,0],[[9,0],1]],1],1]",
        // `updateRightmost` should be self-called
        "[[[1,[0,0]],[[[9,9],1],1]],1]" to "[[[1,[0,9]],[[0,10],1]],1]",
    )

    @Test
    fun explode() {
        for ((strBeforeExplode, strAfterExplode) in numbersToExplode) {
            val beforeExplode = reader.readNumber(strBeforeExplode)
            SnailfishNumberExploder().explode(beforeExplode)
            assertEquals(
                expected = strAfterExplode,
                actual = beforeExplode.toString(),
                message = "Exploded numbers are different"
            )
        }
    }
}
