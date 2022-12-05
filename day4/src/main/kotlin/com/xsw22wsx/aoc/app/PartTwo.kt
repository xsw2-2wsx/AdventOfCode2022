package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {
    input
        .split("\n")
        .map { it.split(",") }
        .map { it[0] to it[1] }
        .map { it.first.split("-") to it.second.split("-") }
        .map { it.first[0].toInt()..it.first[1].toInt() to it.second[0].toInt() .. it.second[1].toInt() }
        .count { it.first overlapsWith it.second }
        .let { println(it) }
}

infix fun IntRange.overlapsWith(other: IntRange) = first <= other.last && last >= other.first
