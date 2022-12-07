package com.xsw22wsx.aoc.app

import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {

    val stacks = readStacks()

    for (move in readMoves()) {
        val index = stacks[move.from].size - move.amount

        repeat(move.amount) {
            stacks[move.to].add(stacks[move.from].removeAt(index))
        }
    }

    println(stacks.map { it.lastOrNull() }.filterNotNull().joinToString(separator = ""))
}

