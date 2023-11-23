
import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Double, val y: Double)

private class Triangle(val versh1: Point, val versh2: Point, val versh3: Point) {
    fun circleCenter(): Point {
        val a = versh2.x - versh1.x
        val b = versh2.y - versh1.y
        val c = versh3.x - versh1.x
        val d = versh3.y - versh1.y

        val e = a * (versh1.x + versh2.x) + b * (versh1.y + versh2.y)
        val f = c * (versh1.x + versh3.x) + d * (versh1.y + versh3.y)

        val g = 2.0 * (a * (versh3.y - versh2.y) - b * (versh3.x - versh2.x))

        val centerX = (d * e - b * f) / g
        val centerY = (a * f - c * e) / g

        return Point(centerX, centerY)
    }

    fun circleRadius(center: Point): Double {
        return sqrt((center.x - versh1.x).pow(2) + (center.y - versh1.y).pow(2))
    }
}

fun main() {
    println("Введите координаты вершин треугольника(x y):")

    val versh1 = readPoint("Введите координаты вершины 1: ")
    val versh2 = readPoint("Введите координаты вершины 2: ")
    val versh3 = readPoint("Введите координаты вершины 3: ")

    val triangle = Triangle(versh1, versh2, versh3)

    val circleCenter = triangle.circleCenter()
    val circleRadius = triangle.circleRadius(circleCenter)

    println("Центр описанной окружности: (${circleCenter.x.format(5)}, ${circleCenter.y.format(5)})")
    println("Радиус описанной окружности: ${circleRadius.format(5)}")
}

fun readPoint(prompt: String): Point {
    print(prompt)
    val input = readlnOrNull() ?: throw IllegalArgumentException("Ошибка ввода")
    val (x, y) = input.split(" ").map { it.toDouble() }
    return Point(x, y)
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)