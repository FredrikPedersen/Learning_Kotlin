# Notes

## Index

1. [General](#1-general)  
	1.1 [Null Handling](#null-handling)  
   	1.2 [Exceptions](#exceptions)
2. [Statements](#2-statements)  
   	2.1 [Statements as Expressions](#statements-as-expressions)  
	2.2 [When Statement](#when-statement)
3. [Loops](#3-loops)  
	3.1 [For-loops](#for-loops)  
4. [Functions](#4-functions)  
	4.1 [Default Parameters](#default-parameters)

## 1 General

 - var is for mutable variables.
 - val is for immutable values.
 - public access modifier is default behaviour in Kotlin when nothing else is specified.
 - Unit is Kotlin for Void
 - Most statements (like if, when and try) functions as expressions, thus has implicit return values.
 
### Null Handling
 
  - ?-operator on a value indicates that it can be null
  - ?-operator on a method call functions as a null check on the object calling the function, safely performing the operation even if the object is null.
 
 ```Kotlin
 
//text initialized to null, call to .length outputs null.
val text: String? = null
print(text?.length)
	
//text initialized to String-value, call to .length outputs 11
val text: String? = "Hello There"
print(text?.length)
 ````

### Exceptions

- In Kotlin it is not required to rethrow nor catch exceptions from classes throwing exceptions, due to the usage of unchecked exceptions.
	- However, you CAN do so.

## 2 Statements

### Statements as Expressions

 - Statements in Kotlin can be used as expressions

```Kotlin
fun biggestNumber(value1: Int, value2: Int): Int {
	return if (value1 > value2) {
		value1
	} else {
		value2
	}
}
```
 
### When-statement

 - The when statement is Kotlin's alternative to C-like language's switch statement.

```Kotlin

//imagine class Question with string attributes question, answer and correctanswer
fun solution(): String {
	val message = "Question: $question\nYou answered: $answer, which is "

	return when(answer) {
	    correctAnswer -> message + "correct"
		null -> "You haven't answered yet!"
		else -> message + "wrong"
	}
}
```

## 3 Loops

 - Kotlin's for loops are range based (like in Python)
 - Ranges can be over any datatype that implements the comparable interface, e.g:
	- 1..10
	- 'a'..'z'
	
### For-loops

```Kotlin
for (i in 1..4) {
    print(i) //1, 2, 3, 4
}
	
for (i in 1..10 step 2) {
    print(i) //1, 3, 5, 7, 9
}
	
for (i in 10 downTo 1 step 2) {
    print(i) //10, 8, 6, 4, 2
}
	
val numbers = listOf(1,2,3)
	
for (i in numbers) {
    print(i) //1, 2, 3
}
	
for ((index, element) in numbers.withIndex()) {
    println("$element at $index") //1 at 0, 2 at 1, 3 at 2
}
	
val ages = TreeMap<String, Int>()
ages["Fredrik"] = 27
ages["Thomas"] = 26
ages["Joakim"] = 25
    
for ((name, age) in ages) {
    println("$name is $age")
}
```
	
## 4 Functions

### Default Parameters

 - Kotlin supports default parameters in functions.
 - The default value is utilized if no value is passed for the parameter.
 - Note that Java does not support default parameters, so if the Kotlin code is to be supported in Java, all parameters must be passed, or the ***@JvmOverloads*** annotation must be added to the function.
	- ***@JvmOverloads*** makes the Kotlin compiler add overloaded functions, allowing the function to be called with or without passing a value for the default parameter, and using the default value if no 
	value is passed, thus behaving like one function with a default parameter.

```Kotlin

@JvmOverloads
fun logger(message: String, repeat: Int = 1) {
    for (i in 1..repeat) {
        println(message)
	}
}

logger("Hello There") //prints once
logger("Hello There", 10) //prints ten times
```