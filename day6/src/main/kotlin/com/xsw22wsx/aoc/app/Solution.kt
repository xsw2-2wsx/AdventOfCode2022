package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking

const val DISTINCT_CHAR_AMOUNT = 14

fun main(): Unit = runBlocking {
    for(i in 0 .. input.lastIndex)
        if(input.subSequence(i, i + DISTINCT_CHAR_AMOUNT).toSet().size == DISTINCT_CHAR_AMOUNT) {
            println(i + DISTINCT_CHAR_AMOUNT)
            break
        }
}