fun main() {
    println(readNumber("[1,2]".iterator()))
    println(readNumber("[[1,2],3]".iterator()))
    println(readNumber("[9,[8,7]]".iterator()))
    println(readNumber("[[1,9],[8,5]]".iterator()))
    println(readNumber("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]".iterator()))
    println(readNumber("[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]".iterator()))
    println(readNumber("[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]".iterator()))
}

class SnailfishNumber(var left: SnailfishNumber?, var right: SnailfishNumber?, var value: Int?) {
    constructor(left: SnailfishNumber, right: SnailfishNumber) : this(left, right, null)
    constructor(value: Int) : this(null, null, value)
    override fun toString(): String {
        if (value != null)
            return value.toString()
        return "[$left,$right]"
    }
}

fun readNumber(numberIterator: CharIterator) : SnailfishNumber {
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
