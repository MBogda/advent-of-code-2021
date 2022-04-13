package advent18

class SnailfishNumberReader(number: String) {

    private val numberIterator: CharIterator
    private var currentChar: Char = '\n'

    init {
        numberIterator = number.iterator()
    }

    fun read(): SnailfishNumber {
        var left = SnailfishNumber(0)
        var right = SnailfishNumber(0)
        readNextChar()
        while (currentChar != '\n') {
            when {
                currentChar == '[' -> {
                    left = this.read()
                }
                currentChar.isDigit() -> {
                    val builder = StringBuilder().append(currentChar)
                    while (readNextChar() && currentChar.isDigit()) {
                        builder.append(currentChar)
                    }
                    return SnailfishNumber(builder.toString().toInt())
                }
                currentChar == ',' -> {
                    right = this.read()
                }
                currentChar == ']' -> {
                    readNextChar()
                    return SnailfishNumber(left, right)
                }
            }
        }
        return SnailfishNumber(0)
    }

    private fun readNextChar(): Boolean {
        if (numberIterator.hasNext()) {
            currentChar = numberIterator.nextChar()
            return true
        }
        currentChar = '\n'
        return false
    }
}
