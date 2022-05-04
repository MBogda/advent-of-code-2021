package advent19

internal object ScannersTestData {
    const val OVERLAPPING_BEACONS = 3

    // the first 3 beacons are always the same in all scanners, and the 4th is always an extra one

    // the main scanner
    val mainScanner = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),
        )
    )

    val mainScannerRelativeMatrix = listOf(
        setOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),
        ),
        setOf(
            Beacon(-1, -2, -3),
            Beacon(0, 0, 0),
            Beacon(-2, -1, -4),
            Beacon(99, 98, 97),
        ),
        setOf(
            Beacon(1, -1, 1),
            Beacon(2, 1, 4),
            Beacon(0, 0, 0),
            Beacon(101, 99, 101),
        ),
        setOf(
            Beacon(-100, -100, -100),
            Beacon(-99, -98, -97),
            Beacon(-101, -99, -101),
            Beacon(0, 0, 0),
        ),
    )

    // this scanner is moved by (10, 10, 10), but has the same direction as the main one
    val movedScanner = Scanner(
        1, mutableListOf(
            Beacon(10, 10, 10),
            Beacon(11, 12, 13),
            Beacon(9, 11, 9),
            Beacon(100, 100, 100),
        )
    )

    val expectedVectorsMoved = Pair(
        TurningVector.fromString("(x,y,z)"),
        Vector(-10, -10, -10),
    )

    val expectedUnifiedWithMoved = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // move back by (-10, -10, -10)
            Beacon(90, 90, 90),
        )
    )

    val expectedMaxDistanceMoved = 30

    // this scanner is turned as (-z, -y, -x), but it's in the same point as the main one
    val turnedScanner = Scanner(
        2, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(-3, -2, -1),
            Beacon(1, -1, 1),
            Beacon(100, 100, 100),
        )
    )

    val expectedVectorsTurned = Pair(
        TurningVector.fromString("(-z,-y,-x)"),
        Vector(0, 0, 0),
    )

    val expectedUnifiedWithTurned = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // turn back by (-z, -y, -x)
            Beacon(-100, -100, -100),
        )
    )

    val expectedMaxDistanceTurned = 0

    // this scanner is moved by (-10, -10, -10) and then turned as (-y, z, -x)
    val movedAndTurnedScanner = Scanner(
        0, mutableListOf(
            // after the moving coordinates are:
//            Beacon(-10, -10, -10),
//            Beacon(-9, -8, -7),
//            Beacon(-11, -9, -11),

            // after the turning coordinates are:
            Beacon(10, -10, 10),
            Beacon(8, -7, 9),
            Beacon(9, -11, 11),
            Beacon(100, 100, 100),
        )
    )

    val expectedVectorsMovedAndTurned = Pair(
        TurningVector.fromString("(-z,-x,y)"),
        Vector(10, 10, 10),
    )

    val expectedUnifiedWithMovedAndTurned = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            // turn back by (-z, -x, y)
//            Beacon(-100, -100, 100),
            // then move back by (10, 10, 10)
            Beacon(-90, -90, 110),
        )
    )

    val expectedMaxDistanceMovedAndTurned = 30

    val expectedUnifiedAll = Scanner(
        0, mutableListOf(
            Beacon(0, 0, 0),
            Beacon(1, 2, 3),
            Beacon(-1, 1, -1),
            Beacon(100, 100, 100),

            Beacon(90, 90, 90),
            Beacon(-100, -100, -100),
            Beacon(-90, -90, 110),
        )
    )

    val expectedMaxDistanceAll = 60
}
