package advent18

class SnailfishNumberExploder {
    var level = 0
    // todo? leftNumber, rightNumber as class fields

    fun explode(number: SnailfishNumber) {
        explodeWithResult(number)
    }

    private fun explodeWithResult(number: SnailfishNumber): ExplodedPair {
        level++
        if (level > 4 && number.value == null) {
            val leftNumber = number.left?.value ?: 0
            val rightNumber = number.right?.value ?: 0
            return ExplodedPair(
                exploded = true,
                firstExploded = true,
                leftNumber = leftNumber,
                rightNumber = rightNumber
            )
        } else {
            // todo: make separate functions
            number.left?.let { leftChild ->
                val (exploded, firstExploded, leftNumber, rightNumber) = explodeWithResult(leftChild)
                if (exploded) {
                    if (rightNumber != 0) {
                        number.right?.let { rightChild ->
                            updateLeftmost(rightChild, rightNumber)
                        }
                    }
                    if (firstExploded) {
                        leftChild.value = 0
                        leftChild.left = null
                        leftChild.right = null
                    }
                    return ExplodedPair(
                        exploded = true,
                        firstExploded = false,
                        leftNumber = leftNumber,
                        rightNumber = 0
                    )
                }
            }
            number.right?.let { rightChild ->
                val (exploded, firstExploded, leftNumber, rightNumber) = explodeWithResult(rightChild)
                if (exploded) {
                    if (leftNumber != 0) {
                        number.left?.let { leftChild ->
                            updateRightmost(leftChild, leftNumber)
                        }
                    }
                    if (firstExploded) {
                        rightChild.value = 0
                        rightChild.left = null
                        rightChild.right = null
                    }
                    return ExplodedPair(
                        exploded = true,
                        firstExploded = false,
                        leftNumber = 0,
                        rightNumber = rightNumber
                    )
                }
            }
        }
        level--
        return ExplodedPair(exploded = false, firstExploded = false, leftNumber = 0, rightNumber = 0)
    }

    private fun updateLeftmost(number: SnailfishNumber, updated: Int) {
        number.left?.let { updateLeftmost(it, updated) }
            ?: run {
                number.value = number.value?.let { it + updated }
            }
    }

    private fun updateRightmost(number: SnailfishNumber, updated: Int) {
        number.right?.let { updateRightmost(it, updated) }
            ?: run {
                number.value = number.value?.let { it + updated }
            }
    }

    private data class ExplodedPair(
        val exploded: Boolean,
        val firstExploded: Boolean,
        val leftNumber: Int,
        val rightNumber: Int
    )
}
