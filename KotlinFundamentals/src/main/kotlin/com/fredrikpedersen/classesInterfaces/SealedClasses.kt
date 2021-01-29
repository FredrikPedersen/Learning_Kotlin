package com.fredrikpedersen.classesInterfaces

sealed class PersonEvent {
    class Awake: PersonEvent()
    class Asleep: PersonEvent()
    class Eating(val food: String) : PersonEvent()

}

fun handlePersonEvent(event: PersonEvent) {
    when(event) {
        is PersonEvent.Awake -> println("Awake")
        is PersonEvent.Asleep -> println("Sleeping")
        is PersonEvent.Eating -> println(event.food)
    }
}