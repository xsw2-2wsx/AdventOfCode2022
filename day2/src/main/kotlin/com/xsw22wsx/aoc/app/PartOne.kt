package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    input
        .split("\n")
        .map { it.split(" ").map(::translate) }
        .map { score(it[0], it[1]) }
        .sum()
        .let { println(it) }

}

sealed interface Option {
    val beats: Option
    val losesTo: Option
}

object ROCK : Option {
    override val beats: Option = SCISSORS
    override val losesTo: Option = PAPER
}
object PAPER : Option {
    override val beats: Option = ROCK
    override val losesTo: Option = SCISSORS
}

object SCISSORS : Option {
    override val beats: Option = PAPER
    override val losesTo: Option = ROCK
}


fun translate(text: String): Option = when(text) {
    "X", "A" -> ROCK
    "B", "Y" -> PAPER
    "C", "Z" -> SCISSORS
    else -> throw IllegalStateException("WTF is $text")
}

fun score(opponent: Option, you: Option) = resultScore(opponent, you) + optionScore(you)

fun resultScore(opponent: Option, you: Option): Int = when {
    you.beats == opponent -> 6
    you == opponent -> 3
    you.losesTo == opponent -> 0
    else -> throw IllegalStateException()
}

fun optionScore(option: Option) = when(option) {
    ROCK -> 1
    PAPER -> 2
    SCISSORS -> 3
}