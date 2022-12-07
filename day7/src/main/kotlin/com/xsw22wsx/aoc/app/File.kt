package com.xsw22wsx.aoc.app

class File(
    override var parent: Node,
    override var size: Long,
    override var name: String,
) : Node {
    override var children: MutableList<Node>
        get() { throw IllegalStateException() }
        set(_) { throw IllegalStateException() }

}