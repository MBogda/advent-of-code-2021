package advent18

object SnailfishNumberMagnitudeFinder {
    fun findMagnitude(number: SnailfishNumber): Int {
        number.value?.let { return it }
        val left = number.left?.let { findMagnitude(it) } ?: 0
        val right = number.right?.let { findMagnitude(it) } ?: 0
        return left * 3 + right * 2
    }
}
