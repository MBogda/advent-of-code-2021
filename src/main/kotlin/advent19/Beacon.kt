package advent19

import kotlin.math.abs

/**
 * [Beacon] and [Vector] have the same form (coordinates in 3 dimensions), but different meanings.
 * [Beacon] represents coordinates of a particular point (the point where the beacon is),
 * while [Vector] represents how (by adding which coordinates) to go from one point to another.
 */
typealias Vector = Beacon

/**
 * Coordinates of a beacon relative to it's [Scanner] position.
 */
data class Beacon(val x: Int, val y: Int, val z: Int) {
    /**
     * Returns a new [Vector], representing how to go from [beacon] to this [Beacon].
     */
    fun findVector(beacon: Beacon): Vector {
        val (x1, y1, z1) = beacon
        return Vector(x1 - x, y1 - y, z1 - z)
    }

    /**
     * Returns a new [Beacon], which is the [beacon] moved by this [Vector].
     */
    fun move(beacon: Beacon): Beacon {
        val (x1, y1, z1) = beacon
        return Beacon(x1 + x, y1 + y, z1 + z)
    }

    /**
     * Returns a Manhattan distance between this [Beacon] and the zero one (0, 0, 0).
     */
    fun manhattanDistance() = abs(x) + abs(y) + abs(z)
}
