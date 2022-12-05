package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    input
        .split("\n")
        .map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .map { it.first.toCharArray() to it.second.toCharArray() }
        .map { compartments -> compartments.first.find { it in compartments.second } }
        .filterNotNull()
        .map { getPriority(it) }
        .sum()
        .let { println(it) }


}

fun getPriority(item: Char) = when(item) {
    in 'a'..'z' -> item.code - 96
    in 'A'..'Z' -> item.code - 38
    else -> throw IllegalStateException("WTF is $item")
}
