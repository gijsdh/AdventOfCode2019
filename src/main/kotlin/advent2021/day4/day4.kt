package advent2021.day4

fun main(args: Array<String>) {

    var sum = 0
    var sum2 = 0
    for (i in 134564 until 585159 + 1) {
        var digits = IntArray(6)
        var count = 0
        for (char in i.toString()) digits[count++] = char.digitToInt()

        var hasDouble = false
        var isIncreasing = false
        var hasExactlyDouble = false
        for (i in 0 until digits.size - 1) if (digits[i] == digits[i + 1]) {
            hasDouble = true

            if (i == 0) {
                if (digits[i] != digits[i + 2]) hasExactlyDouble = true
            } else if (i == digits.size - 2) {
                if (digits[i] != digits[i -1]) hasExactlyDouble = true
            } else if(digits[i] != digits[i + 2] && digits[i] != digits[i -1]) {
                hasExactlyDouble = true
            }
        }
        
        for (i in 0 until digits.size - 1) if (digits[i] > digits[i + 1]) {
            isIncreasing = true;
            break
        }
        
        if (hasDouble && !isIncreasing) sum += 1
        if (hasDouble && !isIncreasing && hasExactlyDouble) sum2 += 1
    }

    println("Answer A = " + sum)
    println("Answer B = " + sum2)
}