package advent18

fun main() {
    val reader = SnailfishNumberReader()
    println(reader.readNumber("[1,2]"))
    println(reader.readNumber("[[1,2],3]"))
    println(reader.readNumber("[9,[8,7]]"))
    println(reader.readNumber("[[1,9],[8,5]]"))
    println(reader.readNumber("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]"))
    println(reader.readNumber("[[[9,[3,8]],[[0,9],6]],[[[3,7],[4,9]],3]]"))
    println(reader.readNumber("[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]"))
}

data class SnailfishNumber(var left: SnailfishNumber?, var right: SnailfishNumber?, var value: Int?) {
    constructor(left: SnailfishNumber, right: SnailfishNumber) : this(left, right, null)
    constructor(left: Int, right: Int) : this(SnailfishNumber(left), SnailfishNumber(right))
    constructor(left: Int, right: SnailfishNumber) : this(SnailfishNumber(left), right)
    constructor(left: SnailfishNumber, right: Int) : this(left, SnailfishNumber(right))
    constructor(value: Int) : this(null, null, value)
    override fun toString(): String {
        return if (value != null) {
            value.toString()
        } else {
            "[$left,$right]"
        }
    }
}
