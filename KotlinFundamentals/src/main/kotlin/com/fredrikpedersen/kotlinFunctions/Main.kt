@file:JvmName("KotlinFunctions")
package com.fredrikpedersen.kotlinFunctions

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



