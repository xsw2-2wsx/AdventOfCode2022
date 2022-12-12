package com.xsw22wsx.aoc.app

import input

data class Instruction(
    val name: String,
    val arg: Int?,
)

fun readInstructions() = input
    .lines()
    .map {
        val split =  it.split(" ")
        val name = split[0]
        val arg = if(split.size == 1) null else split[1].toInt()

        Instruction(name, arg)
    }

fun executeInstructions(instructions: List<Instruction>): Sequence<Int> = sequence {
    yield(1)
    var currentValue = 1
    suspend fun SequenceScope<Int>.setRegister(value: Int) {
        currentValue = value
        yield(value)
    }

    for(instruction in instructions)
        when(instruction.name) {
            "noop" -> setRegister(currentValue)
            "addx" -> {
                setRegister(currentValue)
                setRegister(currentValue + instruction.arg!!)
            }
        }

}

fun main() = executeInstructions(readInstructions())
    .withIndex()
    .filter { (it.index - 19) % 40 == 0 }
    .map { (it.index + 1) * it.value }
    .sum()
    .let(::println)