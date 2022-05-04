package advent19

/**
 * Class to unify all scanners into one.
 *
 * [overlappingBeacons] represents, how many overlapping beacons should two scanners contain
 * to make a unification possible.
 */
class ScannersUnifier(private val overlappingBeacons: Int = 12) {
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
        val (turningVector, movingVector) = findTransformationVectors(firstScanner, secondScanner)
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

    /**
     * Returns a transformation vector - a vector to transform [secondScanner]'s [Beacon]s
     * into [firstScanner]'s [Beacon]s.
     */
    private fun findTransformationVectors(firstScanner: Scanner, secondScanner: Scanner): Pair<TurningVector, Vector>? {
        val firstMatrix = findBeaconsMatrix(firstScanner)
        val secondMatrix = findBeaconsMatrix(secondScanner)

        for ((firstRowIndex, firstRow) in firstMatrix.withIndex()) {
            for ((secondRowIndex, secondRow) in secondMatrix.withIndex()) {
                for (turningVector in TURNING_VECTORS) {
                    var matchedVectors = 0
                    for (secondVector in secondRow) {
                        val turnedSecondVector = turningVector.turn(secondVector)
                        if (turnedSecondVector in firstRow) {
                            matchedVectors++
                        }
                    }
                    if (matchedVectors >= overlappingBeacons) {
                        val turnedSecondBeacon =
                            turningVector.turn(secondScanner.beacons[secondRowIndex])
                        val movingVector = turnedSecondBeacon.findVector(firstScanner.beacons[firstRowIndex])
                        return Pair(turningVector, movingVector)
                    }
                }
            }
        }
        return null
    }

    /**
     * Returns a matrix, where each row corresponds to each [scanner]'s [Beacon]
     * and contains [Vector]s of this [scanner]'s [Beacon]s  relative to that [Beacon].
     */
    private fun findBeaconsMatrix(scanner: Scanner): List<Set<Vector>> {
        val matrix = ArrayList<Set<Vector>>()
        for (beacon1 in scanner.beacons) {
            val row = HashSet<Vector>()
            for (beacon2 in scanner.beacons) {
                row.add(beacon1.findVector(beacon2))
            }
            matrix.add(row)
        }
        return matrix
    }
}
