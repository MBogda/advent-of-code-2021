package advent18

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnailfishNumberExploderTest {

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

    private val numbersNotToExplode = listOf(
        "[1,2]",
        "[1,[2,[3,[4,4]]]]",
        "[[[[4,4],3],2],1]",
        "[[[[4,4],[4,4]],[[4,4],[4,4]]],[[[4,4],[4,4]],[[4,4],[4,4]]]]",
    )

    @Test
    fun explode_numbersToExplode() {
        for ((beforeExplode, afterExplode) in numbersToExplode) {
            val snailfishNumber = SnailfishNumberReader(beforeExplode).read()
            val result = SnailfishNumberExploder(snailfishNumber).explode()
            assertEquals(expected = true, actual = result, message = "Number wasn't exploded.")
            assertEquals(
                expected = afterExplode, actual = snailfishNumber.toString(), message = "Exploded numbers are different"
            )
        }
    }

    @Test
    fun explode_numbersNotToExplode() {
        for (notToExplode in numbersNotToExplode) {
            val snailfishNumber = SnailfishNumberReader(notToExplode).read()
            val result = SnailfishNumberExploder(snailfishNumber).explode()
            assertEquals(expected = false, actual = result, message = "Number was exploded.")
            assertEquals(
                expected = notToExplode,
                actual = snailfishNumber.toString(),
                message = "Not exploded numbers are different"
            )
        }
    }
}
