@file:JvmName("KotlinFunctions")
package com.fredrikpedersen.kotlinFunctions

fun main(args: Array<String>) {
    log("Default Value") //prints once
    log("Default Value Overridden", 10) //prints ten times
    log(logRepeat = 5, message = "Parameters Named") //Parameters called in different order by naming them.
}

@JvmOverloads
fun log(message: String, logRepeat: Int = 1) {
    for (i in 1..logRepeat) {
        println("$message $i")
    }

}
