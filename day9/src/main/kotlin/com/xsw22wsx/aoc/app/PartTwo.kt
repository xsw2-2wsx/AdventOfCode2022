package com.xsw22wsx.aoc.app


const val AMOUNT_OF_KNOTS = 10

fun main() {
    val visitedPositions = LinkedHashSet<Position>()
    visitedPositions.add(ORIGIN)

    val knots = buildList {
        repeat(AMOUNT_OF_KNOTS) {
            add(ORIGIN)
        }
    }.toMutableList()


    for(step in readSteps()) {
        knots[0] = knots[0] + step

        for(i in 0 until knots.lastIndex)
            knots[i + 1] = adjustTail(knots[i], knots[i + 1])

        visitedPositions.add(knots.last())
    }

    println(visitedPositions.size)

}

