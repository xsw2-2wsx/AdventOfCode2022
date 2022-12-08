package com.xsw22wsx.aoc.app

import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {
    val grid = readTreeGrid()

    val result = allPossiblePositions()
        .map { calculateScenicScore(grid, it) }
        .max()

    println(result)

}

fun allPossiblePositions() = sequence {
    for(column in 0 until GRID_SIZE) for(row in 0 until GRID_SIZE) yield(p(column, row))
}


fun calculateScenicScore(grid: Grid, treePosition: Position): Int = setOf(UP, DOWN, LEFT, RIGHT)
    .map { findViewingDistance(grid, treePosition, it) }
    .reduce(Int::times)

private fun findViewingDistance(
    grid: Grid,
    treePosition: Position,
    direction: Position,
): Int {
    var viewDistance = 0
    val testedTreeHeight = grid[treePosition]

    for((position, height) in grid.traverse(treePosition + direction, direction)) {
        if (testedTreeHeight <= height) {
            if(!position.isEdge()) viewDistance++
            break
        }

        viewDistance++
    }

    return viewDistance
}

