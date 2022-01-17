package com.fredrikpedersen.testing

fun main(args: Array<String>) {

    println("this is all in lowercase".capitalize())
}

fun String.capitalize(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}