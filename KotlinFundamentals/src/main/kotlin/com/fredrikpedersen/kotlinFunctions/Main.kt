@file:JvmName("KotlinFunctions")
package com.fredrikpedersen.kotlinFunctions

fun main(args: Array<String>) {
    log("Hello There",10)
}

@JvmOverloads
fun log(message: String, logRepeat: Int = 1) {
    for (i in 1..logRepeat) {
        println("$message $i")
    }

}
