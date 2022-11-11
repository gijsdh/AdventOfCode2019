package advent2021.day8
import getResourceAsText

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val layers = input.lines()
                        .map { it.split("") }
                        .flatten()
                        .filter { it.isNotEmpty() }
                        .map { it.toInt() }


    var counters = IntArray(3);
    var minZero =  999
    var cal = 0

    // 25 pixels wide and 6 pixels tall.
    for (i in 0 until layers.size) {
        counters[layers[i]]++
        if ((i + 1) % 150 == 0) {
            if(counters[0]< minZero) {
                minZero = counters[0]
                cal = counters[1]*counters[2]
            }
            counters = IntArray(3);
        }
    }

    println("answer 1:" + cal);
    var result = IntArray(150);
    var k = 0;
    for (i in 0 until 150) {
        while (layers[i + k] == 2) {
            k += 150
        }
        result[i] = layers[i + k]
        k = 0;
    }

    println("answer 2:" +  result);

    for (i in 0 until result.size) {
        print(" " + if (result[i] == 1) "X" else " ");
        if ((i + 1) % 25 == 0) {
            println();
        }
    }

}




