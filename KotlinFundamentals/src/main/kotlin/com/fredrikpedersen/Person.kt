package com.fredrikpedersen

class Person (var Name: String){

    fun display(): String {
        return "Name: $Name"
    }

    //Takes in a function as parameter, that function takes a string as a parameter and returns nothing.
    fun displayWithLambda(func: (s: String) -> Unit) {
        func(Name);
    }
}