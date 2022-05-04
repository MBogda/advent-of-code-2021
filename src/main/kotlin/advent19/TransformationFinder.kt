package advent19

object TransformationFinder {
    /**
     * Returns a transformation vector - a vector to transform [secondScanner]'s [Beacon]s
     * into [firstScanner]'s [Beacon]s.
     */
    fun findTransformationVectors(firstScanner: Scanner, secondScanner: Scanner): Pair<TurningVector, Vector>? {
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
                    if (matchedVectors >= PropertyStorage.getOverlappingBeacons()) {
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
    internal fun findBeaconsMatrix(scanner: Scanner): List<Set<Vector>> {
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
