package com.xsw22wsx.aoc.app

import input
import kotlinx.coroutines.runBlocking
import java.util.*

fun main(): Unit = runBlocking {

    val fileSystem = buildFileSystemModel()

    fileSystem
        .find { it is Directory && it.size <= 100_000 }
        .sumOf { it.size }
        .let { println(it) }

}


fun Node.find(acc: MutableList<Node> = LinkedList(), predicate: (Node) -> Boolean): List<Node> {
    if(predicate(this)) acc.add(this)

    if(this is Directory || this is RootDirectory) this.children.forEach { it.find(acc, predicate) }

    return acc
}

fun buildFileSystemModel(): RootDirectory {
    val root = RootDirectory()

    var currentDirectory: Node = root

    for (command in readCommands())
        when(command.command) {
            "cd" -> currentDirectory = when(command.arguments.first()) {
                RootDirectory.NAME -> root
                ".." -> currentDirectory.parent
                else -> currentDirectory
                    .children
                    .filterIsInstance<Directory>()
                    .first { it.name == command.arguments.first() }
            }

            "ls" -> handleLs(currentDirectory, command.output)
        }

    return root
}


fun handleLs(parent: Node, output: String) {
//    if(output.isBlank()) return // TODO:
    val nodes = output.lines().map {
        val (size, name) = it.split(" ")

        if(size == "dir") Directory(name, parent)
        else File(parent, size.toLong(), name)
    }

    parent.children.addAll(nodes)
}


fun readCommands(): List<Command> = """\$\s([\w\s\n/\\.]+)""".toRegex().findAll(input).map { it.groupValues[1] }.map {
    val lines = it.lines().filter { it.isNotBlank() }.toMutableList()

    val args = lines.removeFirst().split(" ").toMutableList()
    val command = args.removeFirst()

    Command(command, args, lines.joinToString("\n"))

}.toList()