package com.xsw22wsx.aoc.app

import java.util.*

open class Directory(
    override var name: String,
    override var parent: Node,
    override var children: MutableList<Node> = LinkedList()

) : Node {
    override val size: Long
        get() = children.sumOf { it.size }
}