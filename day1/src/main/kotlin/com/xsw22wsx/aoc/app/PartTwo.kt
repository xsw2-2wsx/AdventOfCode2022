package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking


fun main(): Unit = runBlocking {
    input
        .split("\n\n")
        .map { it.split("\n") }
        .map { it.map { it.toInt() } }
        .map { it.sum() }
        .sorted().reversed()
        .take(3)
        .sum()
        .let { println(it) }

}