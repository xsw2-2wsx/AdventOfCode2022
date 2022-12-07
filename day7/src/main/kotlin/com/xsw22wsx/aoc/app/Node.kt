package com.xsw22wsx.aoc.app

import java.util.*

interface Node {
    var parent: Node
    val size: Long
    var name: String
    var children: MutableList<Node>
}