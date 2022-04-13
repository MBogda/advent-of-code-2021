package advent18

object SnailfishNumberAdder {
    fun add(number1: SnailfishNumber, number2: SnailfishNumber): SnailfishNumber {
        val newNumber = SnailfishNumber(number1, number2)
        reduce(newNumber)
        return newNumber
    }

    private fun reduce(number: SnailfishNumber) {
        while (true) {
            if (SnailfishNumberExploder(number).explode()) {
                continue
            }
            if (SnailfishNumberSplitter.split(number)) {
                continue
            }
            break
        }
    }
}
