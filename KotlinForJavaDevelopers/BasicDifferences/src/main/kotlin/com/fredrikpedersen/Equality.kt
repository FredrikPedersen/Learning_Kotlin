package com.fredrikpedersen

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 24/05/2021 at 14:17
 */

fun main(args: Array<String>) {

    val employee1 = Employee("Fredrik", 26)
    val employee2 = Employee("Thomas", 24)
    val employee3 = Employee("Thomas", 24)

    println(employee1 === employee2) //false
    println(employee2 === employee3) //false
    println(employee1 == employee2) //false
    println(employee2 == employee3) //true
}