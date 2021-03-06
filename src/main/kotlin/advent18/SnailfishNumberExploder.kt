package advent18

class SnailfishNumberExploder(private val mainNumber: SnailfishNumber) {
    var level = 0
    var exploded = false
    var leftNumber = 0
    var rightNumber = 0

    fun explode(): Boolean {
        return explode(mainNumber)
    }

    private fun explode(number: SnailfishNumber): Boolean {
        level++
        if (level > 4 && number.value == null) {
            leftNumber = number.left?.value ?: 0
            rightNumber = number.right?.value ?: 0
            exploded = true

            number.value = 0
            number.left = null
            number.right = null
            return true
        } else {
            explodeLeft(number)
            if (exploded) return true
            explodeRight(number)
            if (exploded) return true
        }
        level--
        return false
    }

    private fun explodeLeft(number: SnailfishNumber) {
        number.left?.let { leftChild ->
            this.explode(leftChild)
            if (exploded) {
                if (rightNumber != 0) {
                    number.right?.let { rightChild ->
                        updateLeftmost(rightChild, rightNumber)
                    }
                }
                rightNumber = 0
                return
            }
        }
    }

    private fun explodeRight(number: SnailfishNumber) {
        number.right?.let { rightChild ->
            this.explode(rightChild)
            if (exploded) {
                if (leftNumber != 0) {
                    number.left?.let { leftChild ->
                        updateRightmost(leftChild, leftNumber)
                    }
                }
                leftNumber = 0
                return
            }
        }
    }

    private fun updateLeftmost(number: SnailfishNumber, updated: Int) {
        number.left?.let { updateLeftmost(it, updated) } ?: run {
            number.value = number.value?.let { it + updated }
        }
    }

    private fun updateRightmost(number: SnailfishNumber, updated: Int) {
        number.right?.let { updateRightmost(it, updated) } ?: run {
            number.value = number.value?.let { it + updated }
        }
    }
}
