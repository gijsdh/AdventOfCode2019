package advent2021.day5

import getResourceAsText

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val numbers = input.split(",").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()

    findValueAtZero(numbers)
}

private fun findValueAtZero(numbers: IntArray): Int {
    var index = 0
    while (true) {
        var operation = numbers[index]
        var optCode = operation
        var digits = IntArray(5)
        var mode: Pair<Int, Int> = Pair(0, 0)

        if (operation.toString().length != 1) {
            var count = 0
            var padded = operation.toString().padStart(5, '0')
            for (char in padded) digits[count++] = char.digitToInt()
            optCode = ("" + digits[digits.size - 2] + digits[digits.size - 1]).toInt()
            mode = Pair(digits[2], digits[1])
        }
        
        if (optCode == 99) {
            break
        }
        
        var indexes: Triple<Int, Int, Int> = getIndexes(numbers, index, mode)

        if (optCode == 1) {
            numbers[indexes.third] = numbers[indexes.first] + numbers[indexes.second]
            index += 4
        } else if (optCode == 2) {
            numbers[indexes.third] = numbers[indexes.first] * numbers[indexes.second]
            index += 4
        } else if (optCode == 3) {
            numbers[indexes.first] = 5
            index += 2
        } else if (optCode == 4) {
            
            println("Check: " + numbers[indexes.first])
            index += 2
        } else if (optCode == 5) {
            if (numbers[indexes.first] != 0) {
                index = numbers[indexes.second]
            } else {
                index += 3
            }
        } else if (optCode == 6) {
            if (numbers[indexes.first] == 0) {
                index = numbers[indexes.second]
            } else {
                index += 3
            }
        } else if (optCode == 7) {
            if (numbers[indexes.first] < numbers[indexes.second]) numbers[indexes.third] =
                1 else numbers[indexes.third] = 0
            index += 4
        } else if (optCode == 8) {
            if (numbers[indexes.first] == numbers[indexes.second]) numbers[indexes.third] =
                1 else numbers[indexes.third] = 0
            index += 4
        }
    }
    return numbers[0]
}

private fun getIndexes(numbers: IntArray, index: Int, mode: Pair<Int, Int>): Triple<Int, Int, Int> {
    var parameter1 = if (mode.first == 1) index + 1 else numbers[index + 1]
    var parameter2 = if (mode.second == 1) index + 2 else numbers[index + 2]
    return Triple(parameter1, parameter2, numbers[index + 3])
}
