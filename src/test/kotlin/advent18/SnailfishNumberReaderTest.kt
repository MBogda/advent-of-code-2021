package advent18

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SnailfishNumberReaderTest {
    
    private val reader = SnailfishNumberReader()

    @Test
    fun readNumber() {
        // todo: use data-driven testing
        // todo? add assertj
        assertEquals(reader.readNumber("[1,2]").toString(), "[1,2]")
        // todo: add equals method
//        assertEquals(reader.readNumber("[1,2]"), SnailfishNumber(SnailfishNumber(1), SnailfishNumber(2)))
    }
}
