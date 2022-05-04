package advent19

val TURNING_VECTORS = listOf(
    TurningVector.fromString("(x,y,z)"),
    TurningVector.fromString("(x,-y,-z)"),
    TurningVector.fromString("(-x,y,-z)"),
    TurningVector.fromString("(-x,-y,z)"),

    TurningVector.fromString("(x,z,-y)"),
    TurningVector.fromString("(x,-z,y)"),
    TurningVector.fromString("(-x,z,y)"),
    TurningVector.fromString("(-x,-z,-y)"),

    TurningVector.fromString("(y,x,-z)"),
    TurningVector.fromString("(y,-x,z)"),
    TurningVector.fromString("(-y,x,z)"),
    TurningVector.fromString("(-y,-x,-z)"),

    TurningVector.fromString("(y,z,x)"),
    TurningVector.fromString("(y,-z,-x)"),
    TurningVector.fromString("(-y,z,-x)"),
    TurningVector.fromString("(-y,-z,x)"),

    TurningVector.fromString("(z,x,y)"),
    TurningVector.fromString("(z,-x,-y)"),
    TurningVector.fromString("(-z,x,-y)"),
    TurningVector.fromString("(-z,-x,y)"),

    TurningVector.fromString("(z,y,-x)"),
    TurningVector.fromString("(z,-y,x)"),
    TurningVector.fromString("(-z,y,x)"),
    TurningVector.fromString("(-z,-y,-x)"),
)

/**
 * A vector containing information about axes turning.
 */
@Suppress("DataClassPrivateConstructor")
data class TurningVector private constructor(val x: Int, val y: Int, val z: Int) {

    companion object {
        private val STRING_REPRESENTATION_PATTERN = """\((?:-?[x-z],){2}-?[x-z]\)""".toRegex()

        fun fromString(stringRepresentation: String): TurningVector {
            if (!STRING_REPRESENTATION_PATTERN.matches(stringRepresentation)) {
                throw IllegalArgumentException("String representation is wrong.")
            }
            val (x, y, z) = stringRepresentation
                .substring(1, stringRepresentation.length - 1) // remove parentheses
                .split(',')
                .map { axisToInteger(it) }
            return TurningVector(x, y, z)
        }

        private fun axisToInteger(axis: String): Int {
            return when (axis) {
                "x" -> 1
                "-x" -> -1
                "y" -> 2
                "-y" -> -2
                "z" -> 3
                "-z" -> -3
                else -> throw IllegalArgumentException("Can't parse axis.")
            }
        }

        private fun integerToAxis(integer: Int): String {
            return when (integer) {
                1 -> "x"
                -1 -> "-x"
                2 -> "y"
                -2 -> "-y"
                3 -> "z"
                -3 -> "-z"
                else -> throw IllegalArgumentException("Can't convert to axis.")
            }
        }
    }

    override fun toString(): String {
        val (xx, yy, zz) = listOf(x, y, z).map { integerToAxis(it) }
        return "($xx,$yy,$zz)"
    }

    /**
     * Return a new [Beacon], which is the [beacon] turned by this [TurningVector].
     */
    fun turn(beacon: Beacon): Beacon {
        val coordinatesList = listOf(beacon.x, beacon.y, beacon.z)
        val resX = if (x > 0) coordinatesList[x - 1] else -coordinatesList[-x - 1]
        val resY = if (y > 0) coordinatesList[y - 1] else -coordinatesList[-y - 1]
        val resZ = if (z > 0) coordinatesList[z - 1] else -coordinatesList[-z - 1]
        return Beacon(resX, resY, resZ)
    }
}
