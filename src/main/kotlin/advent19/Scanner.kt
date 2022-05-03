package advent19

/**
 * Info about scanner and its detected [Beacon]s.
 */
data class Scanner(val number: Int, val beacons: MutableList<Beacon>) {
    fun copy(number: Int = this.number) = Scanner(number, ArrayList(this.beacons))
}
