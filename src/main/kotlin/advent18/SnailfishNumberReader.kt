package advent18

class SnailfishNumberReader {
    fun readNumber(number: String): SnailfishNumber {
        return readNumber(number.iterator())
    }

    private fun readNumber(numberIterator: CharIterator): SnailfishNumber {
        var left = SnailfishNumber(0)
        var right = SnailfishNumber(0)
        while (numberIterator.hasNext()) {
            val ch = numberIterator.nextChar()
            when {
                ch == '[' -> {
                    left = readNumber(numberIterator)
                }
                ch.isDigit() -> {
                    return SnailfishNumber(ch.digitToInt())
                }
                ch == ',' -> {
                    right = readNumber(numberIterator)
                }
                ch == ']' -> {
                    return SnailfishNumber(left, right)
                }
            }
        }
        return SnailfishNumber(0)
    }
}
