package org.jesperancinha.ktd.nonomads

import java.awt.Color

data class Tree(
    val leaves: List<Leaf> = emptyList()
)

data class Leaf(
    val color: Color = Color.GREEN,
    val length: Int = 10
)

val treeCollection = (1..10)
    .map { Tree((1..10).map { Leaf() }) }
