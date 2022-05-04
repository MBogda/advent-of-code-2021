package advent19

import kotlin.math.max

object ScannersDistanceFinder {
    /**
     * Finds the largest Manhattan distance between any two scanners
     */
    fun findScannersMaxDistance(vararg scanners: Scanner): Int {
        val scannersVectors = findScannersCoordinates(*scanners)
        return findMaxDistance(scannersVectors)
    }

    /**
     * Returns list of [scanners]' coordinates according to the first one (including the first one).
     */
    internal fun findScannersCoordinates(vararg scanners: Scanner): List<Vector> {
        if (scanners.isEmpty()) {
            throw IllegalArgumentException("No scanners were provided.")
        }

        val mainScanner = scanners[0].copy()
        val scannersSet = scanners.toMutableSet()
        scannersSet.remove(mainScanner)

        val movingVectors = arrayListOf(Vector(0, 0, 0))
        outer@ while (scannersSet.isNotEmpty()) {
            val mutableIterator = scannersSet.iterator()
            while (mutableIterator.hasNext()) {
                val scanner = mutableIterator.next()
                val (_, movingVector) = TransformationFinder.findTransformationVectors(mainScanner, scanner)
                    ?: continue

                movingVectors.add(movingVector)
                mutableIterator.remove()
                continue@outer
            }

            // if we're here, no transformation vectors were found and no more can be found later
            throw IllegalStateException("Can't find scanner's coordinates, because can't unify them.")
        }
        return movingVectors
    }

    internal fun findMaxDistance(vectors: List<Vector>): Int {
        var maxDistance = 0
        for (vector1 in vectors) {
            for (vector2 in vectors) {
                val distance = vector1.findVector(vector2).manhattanDistance()
                maxDistance = max(maxDistance, distance)
            }
        }
        return maxDistance
    }
}
