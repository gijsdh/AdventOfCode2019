package advent2021.day3

import getResourceAsText
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val directions = input.lines().map { it.split(",").filter { it.isNotBlank() } }

    println(directions)
    
    val pointsA = getPoints(directions[0])
    val pointsB = getPoints(directions[1])

    val intersections = pointsA.keys.intersect(pointsB.keys)
    
    var min = Int.MAX_VALUE
    for (intersection in intersections) {
        val manhattanD = abs(intersection.first) + abs(intersection.second)
        if (manhattanD < min) min = manhattanD

    }
    
    println("Answer A = " + min)

    var minPart2 = Int.MAX_VALUE
    for (key in intersections) {
        val calc = pointsA[key]!! + pointsB[key]!!
        if (calc < minPart2) minPart2 = calc
    }

    println("Answer B = " + minPart2)
}

fun getPoints(directions: List<String>): MutableMap<Pair<Int, Int>, Int> {
    var hashMap: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    val map: HashMap<Char, Pair<Int, Int>> =
        hashMapOf('L' to Pair(-1, 0), 'R' to Pair(1, 0), 'D' to Pair(0, -1), 'U' to Pair(0, 1))
    var dx = 0
    var dy = 0
    var length = 0
    for (direction in directions) {
        var moves = direction.substring(1).toInt()
        for (i in 0 until moves) {
            var pair = map[direction[0]]
            dx += pair!!.first
            dy += pair!!.second
            length += 1
            hashMap.computeIfAbsent(Pair(dx, dy), { a -> length })
        }
    }
    return hashMap
}





//fun checkManhattan(map: Array<CharArray>, x: Int, y: Int, manhattan: Int): Int {
//    val ith = intArrayOf(0, 1, -1, 0)
//    val jth = intArrayOf(1, 0, 0, -1)
//    var sum = 0
//    for (k in 0 until 4) {
//        if (map[x + ith[k]][y + jth[k]] != '.') sum += 1
//    }
//    
//    println(sum)
//    println(abs((size/2)-y) + abs((size/2)-x))
//    if(sum < 2) return manhattan
//    if (abs((size/2)-y) + abs((size/2)-x)  < manhattan) return abs((size/2)-y) + abs((size/2)-x)
//    return manhattan
//}
//
//
//private fun isValidIndex(i: Int, j: Int, l: Int, k: Int): Boolean {
//    if (i < 0 || j < 0 || i >= l || j >= k) return false
//    return true
//}


