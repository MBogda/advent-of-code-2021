package advent18

object SnailfishNumberSplitter {
    fun split(number: SnailfishNumber): Boolean {
        number.value?.let { value ->
            if (value > 9) {
                number.left = SnailfishNumber(value / 2)
                number.right = SnailfishNumber((value + 1) / 2)
                number.value = null
                return true
            }
            return false
        } ?: run {
            number.left?.let { leftChild ->
                val leftSplit = split(leftChild)
                if (leftSplit) {
                    return true
                } else {
                    number.right?.let { rightChild ->
                        return split(rightChild)
                    }
                }
            }
        }
        return false
    }
}
