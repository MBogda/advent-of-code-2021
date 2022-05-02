package advent19

object InputReader {
    private val HEADER_PATTERN: Regex = """--- scanner (\d+) ---""".toRegex()

    fun readScannersData(inputText: List<String>): List<Scanner> {
        val scanners = ArrayList<Scanner>()

        var scannerNumber: Int? = null
        var beacons = ArrayList<Beacon>()
        for (line in inputText) {
            if (line.isEmpty()) {
                continue
            }

            val matchResult = HEADER_PATTERN.matchEntire(line)
            if (matchResult != null) {
                if (scannerNumber != null) {
                    scanners.add(Scanner(scannerNumber, beacons))
                }
                scannerNumber = matchResult.groupValues[1].toInt()
                beacons = ArrayList()
            } else if (scannerNumber != null) {
                val (x, y, z) = line.split(',').map { it -> it.toInt() }
                beacons.add(Beacon(x, y, z))
            }
        }

        return scanners
    }
}
