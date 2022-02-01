package com.fredrikpedersen.testing

import java.io.File

fun main(args: Array<String>) {

    File(".").walkTopDown().forEach { println(it) }
}