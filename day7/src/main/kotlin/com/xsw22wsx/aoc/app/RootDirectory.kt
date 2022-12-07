package com.xsw22wsx.aoc.app

import java.util.*

open class RootDirectory(
    override var children: MutableList<Node> = LinkedList()
) : Node {

    companion object {
        const val NAME: String = "/"
    }

    override val size: Long
        get() = children.sumOf { it.size }
    override var name: String = "/"
    override var parent: Node
        get() = this
        set(value) {}
}