package converter

import java.math.BigInteger

enum class STATE {
    MENU, CONVERT
}

fun decimalTo(value: BigInteger, base: Int): String {
    var str = ""
    var number = value
    while (number > BigInteger.ZERO) {
        val remainder = number % base.toBigInteger()
        if (remainder < BigInteger.TEN) str += remainder else str += 'a' + (remainder.toInt() - 10)
        number /= base.toBigInteger()
    }
    return str.reversed()
}

fun toDecimal(value: String, base: Int): BigInteger {
    var result = 0.toBigInteger()
    value.forEach {
        result = result * base.toBigInteger() + (if (it >= 'a') it - 'a' + 10 else it - '0').toBigInteger()
    }
    return result
}

fun getMessage(state: STATE, sourceBase: Int, targetBase: Int) {
    when (state) {
        STATE.MENU -> println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        STATE.CONVERT -> println(
            "Enter number in base $sourceBase to convert " +
                    "to base $targetBase (To go back type /back)"
        )
    }
}


fun main() {
    var state = STATE.MENU
    var sourceBase = 0
    var targetBase = 0

    while (true) {
        getMessage(state, sourceBase, targetBase)
        val action = readln()
        when (state) {
            STATE.MENU -> {
                if (action == "/exit") break
                val list = action.split(" ")
                sourceBase = list[0].toInt()
                targetBase = list[1].toInt()
                state = STATE.CONVERT
            }

            STATE.CONVERT -> {
                if (action == "/back") {
                    state = STATE.MENU
                    continue
                }

                if (sourceBase == 10) {
                    println("Conversion result: " + decimalTo(action.toBigInteger(), targetBase))
                } else if (targetBase != 10) {
                    println("Conversion result: " + decimalTo(toDecimal(action, sourceBase), targetBase))
                } else {
                    println("Conversion result: " + toDecimal(action, sourceBase))
                }
            }
        }
    }
}
