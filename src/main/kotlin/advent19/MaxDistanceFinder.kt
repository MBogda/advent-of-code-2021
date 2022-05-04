package advent19

import kotlin.math.max

object MaxDistanceFinder {
    /**
     * Finds the largest Manhattan distance between any two scanners.
     */
    fun findScannersMaxDistance(vararg scanners: Scanner): Int {
        val scannersVectors = ScannersUnifier.unifyScannersAndGetCoordinates(*scanners)
        return findMaxDistance(scannersVectors)
    }

    /**
     * Finds the largest Manhattan distance between any two vectors.
     */
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
