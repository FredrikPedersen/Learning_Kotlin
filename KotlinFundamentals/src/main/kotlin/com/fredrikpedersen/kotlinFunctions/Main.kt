@file:JvmName("KotlinFunctions")
package com.fredrikpedersen.kotlinFunctions

import java.math.BigInteger

fun main(args: Array<String>) {
    logDefaultParam("Default Value") //prints once
    logDefaultParam("Default Value Overridden", 10) //prints ten times
    logDefaultParam(logRepeat = 5, message = "Parameters Named") //Parameters called in different order by naming them.

    val text = "With   Multiple   Whitespace"
    println(replaceMultipleWhiteSpace(text)) //Usage of utility function as a static function
    println(text.replaceMultipleWhiteSpaceExtension()) //Usage og utility function as an extension function

    val h1 = Header("H1")
    val h2 = Header("H2")
    val h3 = h1 plus h2
    println(h3.Name)

    println(fibonacci(10000, BigInteger("1"), BigInteger("0"))) //results in SO without the usage of tailrec-keyword.

}

/* ----- Default and Named Parameters ----- */

@JvmOverloads
fun logDefaultParam(message: String, logRepeat: Int = 1) {
    for (i in 1..logRepeat) {
        println("$message $i")
    }

}

/* ----- Extension Functions ----- */

fun replaceMultipleWhiteSpace(value: String): String {
    val regex = Regex("\\s+")
    return regex.replace(value, " ")
}

fun String.replaceMultipleWhiteSpaceExtension(): String {
    val regex = Regex("\\s+")
    return regex.replace(this, " ")
}

/* ----- Infix Functions ----- */

class Header(var Name: String)

infix fun Header.plus(other: Header) : Header {
    return  Header(this.Name + other.Name)
}

/* ----- Tail Recursive Functions ----- */

tailrec fun fibonacci(n: Int, a: BigInteger, b: BigInteger): BigInteger {
    return if (n == 0) b else fibonacci(n - 1, a + b, a)
}


