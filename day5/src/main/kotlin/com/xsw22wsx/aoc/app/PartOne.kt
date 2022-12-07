package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking
import java.util.LinkedList

const val NUM_STACKS = 9

fun main(): Unit = runBlocking {

    val stacks = readStacks()

    for (move in readMoves()) repeat(move.amount) {
        stacks[move.to].add(stacks[move.from].removeLast())
    }

    println(stacks.map { it.lastOrNull() }.filterNotNull().joinToString(separator = ""))
}


fun readStacks(): List<MutableList<Char>> {
    val stacks: MutableList<MutableList<Char>> = LinkedList()
    repeat(NUM_STACKS) { stacks.add(LinkedList()) }


    val lines = input.split("\n\n")[0].lines().reversed().toMutableList()
    lines.removeFirst()

    lines.forEach { line ->
        line.toCharArray()
            .withIndex()
            .groupBy { it.index / 4 }
            .map { stackEntryOrEmpty -> stackEntryOrEmpty.value.map { it.value }.joinToString(separator = ""); }
            .map { """\w""".toRegex().find(it)?.value?.get(0) }
            .forEachIndexed { i, v -> if(v != null) stacks[i].add(v) }
    }

    return stacks
}

data class Move(
    val amount: Int,
    val from: Int,
    val to: Int,
)

fun readMoves(): List<Move> = input
    .split("\n\n")[1]
    .lines()
    .map {
        val (amount, from, to)  = """\d+""".toRegex().findAll(it).map { it.value.toInt() }.toList()
        Move(amount, from - 1, to - 1)
    }