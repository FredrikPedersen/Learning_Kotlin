package com.fredrikpedersen.inheritance

fun main(args: Array<String>) {
    val laserPrinter = LaserPrinter("Canon 1234")
    laserPrinter.printModel()
}

abstract class Printer(val modelName: String) {

    open fun printModel() = println("Printer model is $modelName")

    abstract fun bestSellingPrice(): Double
}

class LaserPrinter(modelName: String): Printer(modelName) {

    override fun printModel() = println("Laser printer model is $modelName")

    override fun bestSellingPrice(): Double = 1299.00
}