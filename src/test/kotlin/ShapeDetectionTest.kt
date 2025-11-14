import com.example.Shape
import com.example.detectShape
import kotlin.test.Test
import kotlin.test.assertEquals

class ShapeDetectionTest {
    @Test
    fun `detects rectangle`() {
        assertEquals(
            Shape.RECTANGLE,
            detectShape(90, 90, 90, 90)
        )
    }

    @Test
    fun `detects parallelogram`() {
        assertEquals(
            Shape.PARALLELOGRAM,
            detectShape(110, 70, 110, 70)
        )

        assertEquals(
            Shape.PARALLELOGRAM,
            detectShape(80, 100, 80, 100)
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

    @Test
    fun `invalid when any angle is zero or negative`() {
        assertEquals(
            Shape.INVALID,
            detectShape(0, 90, 90, 180)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, 0, 90, 180)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, 90, 0, 180)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, 90, 180, 0)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(-10, 90, 150, 130)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, -10, 150, 130)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(150, 90, -10, 130)
        )
        assertEquals(
            Shape.INVALID,
            detectShape(130, 90, 150, -10)
        )
    }

    @Test
    fun `invalid parallelogram candidates`() {
        assertEquals(
            Shape.INVALID,
            detectShape(110, 70, 110, 60) // b != d
        )
        assertEquals(
            Shape.INVALID,
            detectShape(100, 70, 110, 70) // c != a
        )
        assertEquals(
            Shape.INVALID,
            detectShape(90, 45, 90, 45) // sum != 360
        )
    }
}