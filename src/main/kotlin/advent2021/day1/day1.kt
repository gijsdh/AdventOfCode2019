fun main(args: Array<String>) {
    val input = getResourceAsText("input.txt")
    val inputTest = getResourceAsText("inputExample.txt")
    val inputLines = input.lines().map { it.toInt() }

    var sumPart1 = 0
    var sumPart2 = 0
    for (line in inputLines) {
        var subSum = 0
        var temp = line
        while (true) {
            temp = (temp / 3) - 2
            if (temp < 0) break
            subSum += temp
        }
        sumPart1 += (line / 3) - 2
        sumPart2 += subSum
    }
    println(sumPart1)
    println(sumPart2)
}

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}