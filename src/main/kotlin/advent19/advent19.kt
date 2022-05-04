package advent19

fun main() {
    val inputText: List<String> = object {}.javaClass.getResourceAsStream("input")?.bufferedReader()?.readLines()
        ?: throw AssertionError("Can't read input file.")

    val beaconsNumber = findAllBeacons(inputText)
    println("Part one: $beaconsNumber")   // 353

    val maxDistance = findLargestDistance(inputText)
    println("Part two: $maxDistance")
}

fun findAllBeacons(inputText: List<String>): Int {
    val scanners = InputReader.readScannersData(inputText)
    val unifiedScanners = ScannersUnifier.unifyScanners(*scanners.toTypedArray())
    return unifiedScanners.beacons.size
}

fun findLargestDistance(inputText: List<String>): Int {
    val scanners = InputReader.readScannersData(inputText)
    return MaxDistanceFinder.findScannersMaxDistance(*scanners.toTypedArray())
}
