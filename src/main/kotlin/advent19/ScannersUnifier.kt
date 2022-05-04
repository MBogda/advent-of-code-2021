package advent19

/**
 * Class to unify all scanners into one.
 */
object ScannersUnifier {
    /**
     * Unifies all the [scanners] into the copy of the first one. Returns new unified [Scanner].
     */
    fun unifyScanners(vararg scanners: Scanner): Scanner {
        val mainScanner = scanners[0].copy()
        val scannersSet = scanners.toMutableSet()
        scannersSet.remove(mainScanner)

        outer@ while (scannersSet.isNotEmpty()) {
            val mutableIterator = scannersSet.iterator()
            while (mutableIterator.hasNext()) {
                val scanner = mutableIterator.next()
                if (unifyTwoScanners(mainScanner, scanner)) {
                    mutableIterator.remove()
                    continue@outer
                }
            }
            break // no scanners were unified
        }

        return mainScanner
    }

    /**
     * Unifies [secondScanner] into [firstScanner].
     */
    private fun unifyTwoScanners(firstScanner: Scanner, secondScanner: Scanner): Boolean {
        val (turningVector, movingVector) = TransformationFinder.findTransformationVectors(firstScanner, secondScanner)
            ?: return false   // can't unify this two scanners

        val firstBeaconsSet = firstScanner.beacons.toSet()
        for (secondBeacon in secondScanner.beacons) {
            val turnedSecondBeacon = turningVector.turn(secondBeacon)
            val movedSecondBeacon = movingVector.move(turnedSecondBeacon)
            if (movedSecondBeacon !in firstBeaconsSet) {
                firstScanner.beacons.add(movedSecondBeacon)
            }
        }
        return true
    }
}
