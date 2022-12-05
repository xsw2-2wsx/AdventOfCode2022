package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {
    input
        .split("\n")
        .asSequence()
        .withIndex()
        .groupBy { it.index / 3 }
        .map { it.value.map { it.value } }
        .map { it.map { it.toCharArray() } }
        .map { backpacksInGroup -> backpacksInGroup[0].first { it in backpacksInGroup[1] && it in backpacksInGroup[2] } }
        .map { getPriority(it) }
        .sum()
        .let { println(it) }
}