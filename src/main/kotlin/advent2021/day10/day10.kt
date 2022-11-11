package advent2021.day10

import getResourceAsText

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val layers = input.lines().map { it.split("").filter { it.isNotEmpty() } }

    println(inputTest.lines().map { it.split("").filter { it.isNotEmpty() } }.flatten().count { it.equals("#") })


    var asteroids: MutableSet<Asteroid> = mutableSetOf();
    println(layers)

    for (i in 0 until layers.size) {
        for (j in 0 until layers[0].size) {
            if(layers[i][j].equals("#"))
            asteroids.add(Asteroid(i, j))
        }
    }


    //TODO calculate all angles between, points.



}

class Asteroid(var y: Int, var x: Int) {


    override fun toString(): String {
        return "x= " + x + ",y= " + y
    }
}