import com.example.Shape
import com.example.detectShape
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShapeDetectionTest {
    @Test
    fun `detects rectangle`() {
        assertEquals(
            Shape.RECTANGLE,
            detectShape(90, 90, 90, 90)
        )
    }

    @ParameterizedTest
    @MethodSource("generateParallelogramAngles")
    fun `detects parallelogram`(a: Int, b: Int, c: Int, d: Int) {
        assertEquals(
            Shape.PARALLELOGRAM,
            detectShape(110, 70, 110, 70)
        )

        assertEquals(
            Shape.PARALLELOGRAM,
            detectShape(80, 100, 80, 100)
        )

        assertEquals(
            Shape.PARALLELOGRAM,
            detectShape(a, b, c, d)
        )
    }

    @Test
    fun `detects irregular`() {
        assertEquals(
            Shape.IRREGULAR,
            detectShape(100, 90, 100, 70)
        )

        assertEquals(
            Shape.IRREGULAR,
            detectShape(120, 90, 80, 70)
        )
    }
    // ============================================
    // -------- NEGATIVE TEST CASES ---------------
    // ============================================

    @Test
    fun `invalid when sum is not 360`() {
        assertEquals(
            Shape.INVALID,
            detectShape(100, 100, 100, 100)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, 80, 70, 60)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,   90,  90, 180",
        "90,   0,  90, 180",
        "90,  90,   0, 180",
        "90,  90, 180,   0",
        "-10, 90, 150, 130",
        "90, -10, 150, 130",
        "150, 90, -10, 130",
        "130, 90, 150, -10"
    )
    fun `invalid when any angle is zero or negative`(a: Int, b: Int, c: Int, d: Int) {
        assertEquals(
            Shape.INVALID,
            detectShape(a, b, c, d)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "110, 70, 110, 60",
        "100, 70, 110, 70",
        "90, 45, 90, 45"
    )
    fun `invalid parallelogram candidates`(a: Int, b: Int, c: Int, d: Int) {
        assertEquals(
            Shape.INVALID,
            detectShape(a, b, c, d)
        )
    }


    fun generateParallelogramAngles(): Stream<Arguments> {
        val args = mutableListOf<Arguments>()

        for (a in 1..179) {
            val b = 180 - a

            if (a == b) continue

            val c = a
            val d = b

            args += Arguments.of(a, b, c, d)
        }

        return args.stream()
    }
}