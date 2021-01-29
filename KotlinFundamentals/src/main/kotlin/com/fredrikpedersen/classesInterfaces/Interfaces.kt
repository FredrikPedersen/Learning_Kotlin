package com.fredrikpedersen.classesInterfaces

interface Time {
    fun setTime(hours: Int, mins: Int = 0, secs: Int = 0)
    fun setTime(time: FredrikTime) = setTime(time.hours) //Default implementation
}

interface EndOfTheWorld {
    fun setTime(time: FredrikTime) {}
}

class FredrikTime {
    var hours: Int = 0
    var minutes: Int = 0
    var seconds: Int = 0
}

class NorwegianTime : Time, EndOfTheWorld {
    override fun setTime(time: FredrikTime) {
        super<EndOfTheWorld>.setTime(time) //Using the default implementation from EndOfTheWorld.
    }

    override fun setTime(hours: Int, mins: Int, secs: Int) {}
}