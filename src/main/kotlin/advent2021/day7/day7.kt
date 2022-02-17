package advent2021.day7
import advent2021.day6.Planet2
import getResourceAsText
import java.util.*

fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val numbers = input.split(",").filter { it.isNotBlank() }.map{ it.toInt() }.toIntArray()
    
    var permutations = permute(listOf<Int>(0, 1, 2, 3, 4))
    
    var max = 0;
    for (permutation in permutations) {
        var clone = numbers.clone()
        var A = runProgram(clone, permutation[0], 0)
        var B = runProgram(clone, permutation[1], A!!)
        var C = runProgram(clone, permutation[2], B!!)
        var D = runProgram(clone, permutation[3], C!!)
        var E = runProgram(clone, permutation[4], D!!)
        if (E!! > max) max = E
    }
    println( println("Answer A = " + max))
    
    var permutations2 = permute(listOf<Int>(7, 5, 8, 6, 9))
    var max2 = 0
    for (permutation in permutations2) {

        var programs: MutableList<Program> = mutableListOf()
        for (i in 0 until 5) {
            var clone = numbers.clone()
            programs.add(Program(clone))
        }
        var last = 0
        var j = 0
        val phaseInputs = booleanArrayOf(true, true, true, true, true)
        while (!programs[4].isHalted) {
            var currentProgram = programs[j]
            
            if (currentProgram.isWaitingForInput) {
                if(phaseInputs[j])
                {
                    currentProgram.isWaitingForInput = false
                  
                    currentProgram.runProgram(permutation[j])
                    phaseInputs[j] = false;
                } else {
                    currentProgram.isWaitingForInput = false
                    currentProgram.runProgram(last)
                    last = currentProgram.output
                }
            } else {
                assert(false)
            }
            j++;
            if (j == 5)
                j = 0;
        }
        
        var value = programs[4].output
        
        if (value > max2) max2 = value
    }

    println(println("Answer B = $max2"))

}


//https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}


private fun runProgram(numbers: IntArray, input: Int, inputTwo: Int): Int? {
    var index = 0
    var output = -1
    var isFirstInput = true
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
            if (isFirstInput) {
                numbers[indexes.first] = input
                isFirstInput = false
            } else {
                numbers[indexes.first] = inputTwo
            }
            index += 2
        } else if (optCode == 4) {
            return numbers[indexes.first]
            println("Check: " + output)
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
        } else {
            return null
        }
    }
    return output
}


private fun getIndexes(numbers: IntArray, index: Int, mode: Pair<Int, Int>): Triple<Int, Int, Int> {
    var parameter1 = if (mode.first == 1) index + 1 else numbers[index + 1]
    var parameter2 = if (mode.second == 1) index + 2 else numbers[index + 2]
    var parameter3 = if (index + 3 < numbers.size) numbers[index + 3] else 0
    return Triple(parameter1, parameter2, parameter3)
}

class Program(val numbers: IntArray) {
    
    var index = 0
    var isHalted: Boolean = false
    var isWaitingForInput = true;
    var output = 0
    
    fun runProgram(input: Int) {
        var isFirstInput = true
        
        while (!isWaitingForInput && !isHalted) {
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
                isHalted = true;
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
                if (isFirstInput) {
                    numbers[indexes.first] = input
                    isFirstInput = false
                    index += 2
                } else {
                    isWaitingForInput =  true;
                }
            } else if (optCode == 4) {
                output = numbers[indexes.first]
                index += 2
            } else if (optCode == 5) {
                if (numbers[indexes.first] != 0) {
                    index = numbers[indexes.second].toInt()
                } else {
                    index += 3
                }
            } else if (optCode == 6) {
                if (numbers[indexes.first] == 0) {
                    index = numbers[indexes.second].toInt()
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
            } else {
                isHalted = true;
            }
        }
    }
}