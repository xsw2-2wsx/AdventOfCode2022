package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking
import java.util.*

const val TOTAL_SPACE = 70_000_000
const val SPACE_REQUIRED = 30_000_000

fun main(): Unit = runBlocking {
    val fileSystem = buildFileSystemModel()

    val maxOccupiedSpace = TOTAL_SPACE - SPACE_REQUIRED
    val toDelete = fileSystem.size - maxOccupiedSpace

    fileSystem
        .find { it is Directory && it.size >= toDelete }
        .minBy { it.size }
        .let { println(it.size) }

}
