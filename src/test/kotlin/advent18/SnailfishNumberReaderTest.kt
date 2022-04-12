package advent18

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnailfishNumberReaderTest {

    private val reader = SnailfishNumberReader()

    private val stringsToNumbers = mapOf(
        "[1,2]" to SnailfishNumber(1, 2),
        "[[1,2],3]" to SnailfishNumber(SnailfishNumber(1, 2), 3),
        "[9,[8,7]]" to SnailfishNumber(9, SnailfishNumber(8, 7)),
        "[[1,9],[8,5]]" to SnailfishNumber(SnailfishNumber(1, 9), SnailfishNumber(8, 5)),
        "[[[[1,2],[3,4]],[[5,6],[7,8]]],9]" to SnailfishNumber(
            SnailfishNumber(
                SnailfishNumber(SnailfishNumber(1, 2), SnailfishNumber(3, 4)),
                SnailfishNumber(SnailfishNumber(5, 6), SnailfishNumber(7, 8)),
            ), 9
        ),
        "[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]" to SnailfishNumber(
            SnailfishNumber(
                SnailfishNumber(9, SnailfishNumber(3, 8)),
                SnailfishNumber(SnailfishNumber(0, 9), 6),
            ),
            SnailfishNumber(
                SnailfishNumber(
                    SnailfishNumber(3, 7),
                    SnailfishNumber(4, 9),
                ), 3
            ),
        ),
        "[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]" to SnailfishNumber(
            SnailfishNumber(
                SnailfishNumber(
                    SnailfishNumber(1, 3),
                    SnailfishNumber(5, 3),
                ),
                SnailfishNumber(
                    SnailfishNumber(1, 3),
                    SnailfishNumber(8, 7),
                ),
            ),
            SnailfishNumber(
                SnailfishNumber(
                    SnailfishNumber(4, 9),
                    SnailfishNumber(6, 9),
                ),
                SnailfishNumber(
                    SnailfishNumber(8, 2),
                    SnailfishNumber(7, 3),
                ),
            ),
        ),
        // not for the task, but for usefulness: parse multi-digit numbers
        "[10,10000]" to SnailfishNumber(10, 10000),
    )

    @Test
    fun readNumber_InternalRepresentation() {
        // todo: use data-driven testing
        // todo? add assertj
        for ((numberString, snailfishNumber) in stringsToNumbers) {
            assertEquals(
                expected = snailfishNumber,
                actual = reader.readNumber(numberString),
                message = "Internal representations are different"
            )
        }
    }

    @Test
    fun readNumber_StringRepresentation() {
        for ((numberString, _) in stringsToNumbers) {
            assertEquals(
                expected = numberString,
                actual = reader.readNumber(numberString).toString(),
                message = "String representations are different"
            )
        }
    }
}
