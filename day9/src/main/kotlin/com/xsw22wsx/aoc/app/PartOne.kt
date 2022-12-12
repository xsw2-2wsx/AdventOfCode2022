package com.xsw22wsx.aoc.app

import input
import java.lang.Integer.signum
import kotlin.math.abs

data class Position(
    val x: Int,
    val y: Int,
)

fun p(x: Int, y: Int) = Position(x, y)

operator fun Position.plus(other: Position) = p(x + other.x, y + other.y)
infix fun Position.touches(other: Position) = abs(x - other.x) <= 1 && abs(y - other.y) <= 1
infix fun Position.doesNotTouch(other: Position) = !touches(other)

val ORIGIN = p(0, 0) // top left
val RIGHT = p(1, 0)
val UP = p(0, 1)
val DOWN = p(0, -1)
val LEFT = p(-1, 0)

fun main() {
    var head = ORIGIN
    var tail = ORIGIN

    val visitedPositions = LinkedHashSet<Position>()

    visitedPositions.add(ORIGIN)

    for(step in readSteps()) {
        head += step
        tail = adjustTail(head, tail)
        visitedPositions.add(tail)
    }

    println(visitedPositions.size)

}

fun adjustTail(head: Position, tail: Position): Position =
    if(head doesNotTouch tail) tail + calculateDisplacement(head, tail)
    else tail

fun calculateDisplacement(head: Position, tail: Position) = p(signum(head.x - tail.x), signum(head.y - tail.y))

fun readSteps(): List<Position> = input
    .lines()
    .map { line ->
        val (directionString, amount) = line.split(" ")

        val direction = when(directionString) {
            "R" -> RIGHT
            "L" -> LEFT
            "U" -> UP
            "D" -> DOWN
            else -> throw IllegalStateException("WTF is $directionString")
        }

        buildList {
            repeat(amount.toInt()) {
                add(direction)
            }
        }
    }
    .flatten()