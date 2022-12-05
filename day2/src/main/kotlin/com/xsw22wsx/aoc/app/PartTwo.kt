package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {
    input
        .split("\n")
        .map { it.split(" ") }
        .map { translate(it[0]) to findYourMove(translate(it[0]), it[1]) }
        .map { score(it.first, it.second) }
        .sum()
        .let { println(it) }

}

fun findYourMove(opponent: Option, expectedResult: String) = when(expectedResult) {
    "X" -> opponent.beats
    "Y" -> opponent
    "Z" -> opponent.losesTo
    else -> throw IllegalStateException("WTF is $expectedResult")
}
