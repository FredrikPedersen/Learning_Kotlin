package com.fredrikpedersen.classesInterfaces

abstract class Person {
    var firstName: String = ""
    var lastName: String = ""

    fun getName() : String = "$firstName $lastName"
    abstract fun details() : String
}

class Student: Person() {
    var adress: String = ""

    override fun details(): String {
        return "${getName()}, living at $adress"
    }
}
