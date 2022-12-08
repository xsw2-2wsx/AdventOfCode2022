package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking
import kotlin.streams.toList


const val GRID_SIZE = 99

data class Position(
    val row: Int,
    val column: Int
)

fun p(row: Int, column: Int) = Position(row, column)
operator fun <T> List<List<T>>.get(p: Position): T = this[p.row][p.column]

fun Position.isEdge() = column == 0 || column == GRID_SIZE || row == 0 || row == GRID_SIZE
fun Position.isWithinBounds() = column in 0 until GRID_SIZE && row in 0 until GRID_SIZE
operator fun Position.plus(other: Position) = p(row + other.row, column + other.column)


val ORIGIN = p(0, 0)
val DOWN = p(1, 0)
val UP = p(-1, 0)
val RIGHT = p(0, 1)
val LEFT = p(0, -1)


typealias Grid = List<List<Int>>

fun main(): Unit = runBlocking {
    val grid = readTreeGrid()
    val visibleTreePositions: MutableSet<Position> = LinkedHashSet()

    val checksToMake = setOf(
        RIGHT to DOWN,
        DOWN to LEFT,
        LEFT to UP,
        UP to RIGHT,
    )

    var startingPosition = ORIGIN

    for(check in checksToMake)
        edge@for((position, edgeTreeHeight) in  grid.traverse(startingPosition, check.first)) {
            visibleTreePositions.add(position)
            var currentHighestTree = edgeTreeHeight

            lineOfSight@for((positionInLineOfSight, height) in grid.traverse(position, check.second)) {
                if(height > currentHighestTree) {
                    visibleTreePositions.add(positionInLineOfSight)
                    currentHighestTree = height

                    if(height == 9) break@lineOfSight
                }
            }

            startingPosition = position // not ideal :(
        }

    println(visibleTreePositions.size)

}


fun <T> List<List<T>>.traverse(
    start: Position,
    vector: Position,
    amount: Int? = null
): Sequence<Pair<Position, T>> = sequence {
    var currentPosition = start

    var counter = 0
    while((amount == null || counter < amount) && currentPosition.isWithinBounds()) {
        yield(currentPosition to this@traverse[currentPosition])
        currentPosition += vector
        counter++
    }

}

fun readTreeGrid(): Grid = input.lines()
    .map { it.chars().map { it - 48 }.toList() }