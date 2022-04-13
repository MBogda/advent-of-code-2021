package advent18

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