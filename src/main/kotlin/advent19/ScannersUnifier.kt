package advent19

/**
 * [Beacon] and [Vector] have the same form (coordinates in 3 dimensions), but different meanings.
 * [Beacon] represents coordinates of a particular point (the point where the beacon is),
 * while [Vector] represents how (by adding which coordinates) to go from one point to another.
 */
private typealias Vector = Beacon

object ScannersUnifier {
    // todo? overlappingBeacons as a field or something

    /**
     * Unifies all the [scanners] into [mainScanner].
     *
     * [overlappingBeacons] represents, how many overlapping beacons should two scanners contain
     * to make a unification possible.
     */
    fun unifyScanners(overlappingBeacons: Int = 12, mainScanner: Scanner, vararg scanners: Scanner) {
        val scannersSet = scanners.toMutableSet()
        outer@ while (scannersSet.isNotEmpty()) {
            val mutableIterator = scannersSet.iterator()
            while (mutableIterator.hasNext()) {
                val scanner = mutableIterator.next()
                if (unifyTwoScanners(overlappingBeacons, mainScanner, scanner)) {
                    mutableIterator.remove()
                    continue@outer
                }
            }
            break // no scanners were unified
        }
    }

    /**
     * Unifies [secondScanner] into [firstScanner].
     *
     * [overlappingBeacons] represents, how many overlapping beacons should two scanners contain
     * to make a unification possible.
     */
    private fun unifyTwoScanners(overlappingBeacons: Int, firstScanner: Scanner, secondScanner: Scanner): Boolean {
        val (turningVector, movingVector) = findTransformationVectors(
            overlappingBeacons, firstScanner, secondScanner
        ) ?: return false   // can't unify this two scanners

        val firstBeaconsSet = firstScanner.beacons.toSet()
        for (secondBeacon in secondScanner.beacons) {
            val turnedSecondBeacon = TurningVector.applyTurningVector(secondBeacon, turningVector)
            val movedSecondBeacon = applyVector(turnedSecondBeacon, movingVector)
            if (movedSecondBeacon !in firstBeaconsSet) {
                firstScanner.beacons.add(movedSecondBeacon)
            }
        }
        return true
    }

    /**
     * Returns a transformation vector - a vector to transform [secondScanner]'s [Beacon]s
     * into [firstScanner]'s [Beacon]s.
     *
     * [overlappingBeacons] represents, how many overlapping beacons should two scanners contain
     * to make a unification possible.
     */
    private fun findTransformationVectors(
        overlappingBeacons: Int, firstScanner: Scanner, secondScanner: Scanner
    ): Pair<TurningVector, Vector>? {
        val firstMatrix = findBeaconsMatrix(firstScanner)
        val secondMatrix = findBeaconsMatrix(secondScanner)

        for ((firstRowIndex, firstRow) in firstMatrix.withIndex()) {
            for ((secondRowIndex, secondRow) in secondMatrix.withIndex()) {
                for (turningVector in TURNING_VECTORS) {
                    var matchedVectors = 0
                    for (secondVector in secondRow) {
                        val turnedSecondVector = TurningVector.applyTurningVector(secondVector, turningVector)
                        if (turnedSecondVector in firstRow) {
                            matchedVectors++
                        }
                    }
                    if (matchedVectors >= overlappingBeacons) {
                        val turnedSecondBeacon =
                            TurningVector.applyTurningVector(secondScanner.beacons[secondRowIndex], turningVector)
                        val movingVector = findVector(turnedSecondBeacon, firstScanner.beacons[firstRowIndex])
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
                row.add(findVector(beacon1, beacon2))
            }
            matrix.add(row)
        }
        return matrix
    }

    /**
     * Returns a [Vector], representing how to go from [beacon1] to [beacon2].
     */
    private fun findVector(beacon1: Beacon, beacon2: Beacon): Vector {
        val (x1, y1, z1) = beacon1
        val (x2, y2, z2) = beacon2
        return Vector(x2 - x1, y2 - y1, z2 - z1)
    }

    /**
     * Returns a [Beacon], received by applying [vector] to [beacon].
     */
    private fun applyVector(beacon: Beacon, vector: Vector): Beacon {
        val (x1, y1, z1) = beacon
        val (x2, y2, z2) = vector
        return Beacon(x1 + x2, y1 + y2, z1 + z2)
    }
}
