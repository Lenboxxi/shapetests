package com.example
enum class Shape {
    RECTANGLE,
    PARALLELOGRAM,
    IRREGULAR,
    INVALID
}

fun detectShape(a: Int, b: Int, c: Int, d: Int): Shape {
    if (listOf(a, b, c, d).any { it <= 0 }) return Shape.INVALID

    val sum = a + b + c + d

    if (a == 90 && b == 90 && c == 90 && d == 90) {
        return Shape.RECTANGLE
    }

    if (a == c && b == d && a != b && sum == 360) {
        return Shape.PARALLELOGRAM
    }

    if (sum == 360) {
        return Shape.IRREGULAR
    }

    return Shape.INVALID
}