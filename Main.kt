package converter // Do not delete this line

fun decimalTo(value: Int, base: Int): String {
    var str = ""
    var number = value
    while (number > 0) {
        val remainder = number % base
        if (remainder < 10) str += remainder else str += 'a' + (remainder - 10)
        number /= base
    }
    return str.reversed()
}

fun toDecimal(value: String, base: Int): Int {
    var result = 0
    value.forEach { result = result * base + if (it >= 'a') it - 'a' + 10 else it - '0' }
    return result
}

fun main() {
    while (true) {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit) > /to")
        when (readln()) {
            "/from" -> {
                println("Enter number in decimal system:")
                val number = readln().toInt()
                println("Enter target base: ")
                println(
                    "Conversion result: " +
                            decimalTo(number, readln().toInt())
                )
            }

            "/to" -> {
                println("Enter source number:")
                val number = readln()
                println("Enter source base: ")
                println(
                    "Conversion to decimal result: " +
                            toDecimal(number, readln().toInt())
                )
            }

            "/exit" -> break
        }
    }

}