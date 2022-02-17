fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val numbers = input.split(",").filter { it.isNotBlank() }.map { it.toInt() }.toIntArray()

    for (i in 0 until 99) {
        for (j in 0 until 99) {
            var temp = numbers.clone()
            temp[1] = i
            temp[2] = j
            if (findValueAtZero(temp) == 19690720) {
                println("Answer B = " + ((100 * i) + j))
                break
            }
        }
    }
    
    numbers[1] = 12
    numbers[2] = 2
    println("Answer A = " + findValueAtZero(numbers))
}

private fun findValueAtZero(numbers: IntArray): Int {
    var index = 0
    while (true) {
        var indexes: Triple<Int, Int, Int> = getTriple(numbers, index)
        if (numbers[index] == 1) {
            numbers[indexes.third] = numbers[indexes.first] + numbers[indexes.second]
        }
        if (numbers[index] == 2) {
            numbers[indexes.third] = numbers[indexes.first] * numbers[indexes.second]
        }
        if (numbers[index] == 99) {
            break
        }
        index += 4
    }
    return numbers[0]
}

fun getTriple(numbers: IntArray, index : Int): Triple<Int, Int, Int> {
    return Triple(numbers[index + 1], numbers[index + 2], numbers[index + 3])
}

