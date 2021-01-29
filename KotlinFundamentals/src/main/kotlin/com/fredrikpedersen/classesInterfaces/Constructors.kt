package com.fredrikpedersen.classesInterfaces


class Car(var name: String) {

    init {
        print("I have been Initialized")
    }
}

class CarWithDefault(var name: String, val wheels: Int = 4)

class CarWithSecondary(var name: String) {
    constructor(name: String, wheels: Int) : this(name)
}

fun main() {
    val car = Car("Audi")

    val carDefault = CarWithDefault("Audi")
    val carDefault2 = CarWithDefault("Audi", 4)

    val carSecondary = CarWithSecondary("Audi")
    val carSecondary2 = CarWithSecondary("Audi", 4)
}