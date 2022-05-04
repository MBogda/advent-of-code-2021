package advent19

/**
 * Class to unify all scanners into one.
 */
object ScannersUnifier {
    /**
     * Unifies all the [scanners] into the copy of the first one.
     * Returns new unified [Scanner] and all [scanners]' coordinates.
     */
    fun unifyScanners(vararg scanners: Scanner): Pair<Scanner, List<Vector>> {
        if (scanners.isEmpty()) {
            throw IllegalArgumentException("No scanners were provided.")
        }

        val mainScanner = scanners[0].copy()
        val scannersSet = scanners.toMutableSet()
        scannersSet.remove(mainScanner)
        val scannersVectors = arrayListOf(Vector(0, 0, 0))

        outer@ while (scannersSet.isNotEmpty()) {
            val mutableIterator = scannersSet.iterator()
            while (mutableIterator.hasNext()) {
                val scanner = mutableIterator.next()
                val scannersCoordinates = unifyTwoScanners(mainScanner, scanner)
                if (scannersCoordinates != null) {
                    scannersVectors.add(scannersCoordinates)
                    mutableIterator.remove()
                    continue@outer
                }
            }
            break // no scanners were unified
        }

        return Pair(mainScanner, scannersVectors)
    }

    /**
     * Unifies [secondScanner] into [firstScanner].
     * Returns vector with [secondScanner]'s coordinates relative to the [firstScanner].
     */
    private fun unifyTwoScanners(firstScanner: Scanner, secondScanner: Scanner): Vector? {
        val (turningVector, movingVector) = TransformationFinder.findTransformationVectors(firstScanner, secondScanner)
            ?: return null  // can't unify this two scanners

        val firstBeaconsSet = firstScanner.beacons.toSet()
        for (secondBeacon in secondScanner.beacons) {
            val turnedSecondBeacon = turningVector.turn(secondBeacon)
            val movedSecondBeacon = movingVector.move(turnedSecondBeacon)
            if (movedSecondBeacon !in firstBeaconsSet) {
                firstScanner.beacons.add(movedSecondBeacon)
            }
        }
        return movingVector
    }
}
