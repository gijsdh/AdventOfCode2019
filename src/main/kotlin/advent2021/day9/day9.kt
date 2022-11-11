package advent2021.day9

import getResourceAsText

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val layers = input.lines().map { it.split(",")}

    println(layers)
    //TODO
}

