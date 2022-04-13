package advent18

class SnailfishNumberReader(number: String) {

    private val numberIterator: CharIterator
    private var currentChar: Char = '\n'

    init {
        numberIterator = number.iterator()
    }

    fun readNumber(): SnailfishNumber {
        var left = SnailfishNumber(0)
        var right = SnailfishNumber(0)
        while (numberIterator.hasNext()) {
            currentChar = numberIterator.nextChar()
            when {
                currentChar == '[' -> {
                    left = readNumber()
                }
                currentChar.isDigit() -> {
                    return SnailfishNumber(currentChar.digitToInt())
                }
                currentChar == ',' -> {
                    right = readNumber()
                }
                currentChar == ']' -> {
                    return SnailfishNumber(left, right)
                }
            }
        }
        return SnailfishNumber(0)
    }
}
