# Learning Kotlin - Notes

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
	4.1 [Default and Named Parameters](#default-and-named-parameters)  
   	4.2 [Extension Functions](#extension-functions)
   	4.3 [Infix Functions](#infix-functions)
   	4.4 [Tail Recursive Functions](#tail-recursive-functions)
5. [Classes and Interfaces](#5-classes-and-interfaces)  
	5.1 [Interfaces](#interfaces)  
   	5.2 [Classes](#classes-basics)  
   	5.3 [Sealed Classes](#sealed-classes)

## 1 General

 - Var is for mutable variables.
 - Val is for immutable values.
 - Public access modifier is default behaviour in Kotlin when nothing else is specified.  
	- Package private modifier does not exist in Kotlin.
	- Does however have [***internal*** access modifier](https://kotlinlang.org/docs/reference/visibility-modifiers.html), which scopes the visibility to the member's module.
 - Unit is Kotlin for Void.
 
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

 - Statements in Kotlin can be used as expressions, thus has implicit return values.

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

 - Kotlin has support for functions with expresion bodies, these have inferred return types.
 - For functions with no return value, return type (which can be explicitly defined as Unit) can be omitted. 

````Kotlin
//Function with an expression body and inferred return type (int).
fun sum(a: Int, b: Int) = a + b
````

### Default and Named Parameters

 - Kotlin supports default parameters in functions.
 - The default value is utilized if no value is passed for the parameter.
 	- Note that Java does not support default parameters, so if the Kotlin code is to be supported in Java, all parameters must be passed, or the ***@JvmOverloads*** annotation must be added to the function.
	- ***@JvmOverloads*** makes the Kotlin compiler add overloaded functions, allowing the function to be called with or without passing a value for the default parameter, and using the default value if no 
	value is passed, thus behaving like one function with a default parameter.
	  

 - Named parameters are a given a name when the function is called, and is used to specify parameters in whatever order is needed.
 	- Excellent for making it easier to understand what each value passed to a function is.
	- Is a pure Kotlin functionality, and is in no way supported in Java.

```Kotlin
@JvmOverloads
fun logger(message: String, repeat: Int = 1) {
    for (i in 1..repeat) {
        println(message)
	}
}

logger("Default Value") //prints once
logger("Default Value Overridden", 10) //prints ten times
logger(reapeat = 5, message = "Parameters Named") //Parameters called in different order by naming them.
```

### Extension Functions

 - You can "add" functions to classes not owned by you.
 - A use case for this is the case of utility functions, where you in Java would usually create a dedicated Utility class,
and place some static methods in it. In Kotlin, if you wanted som utilities for Strings, you could add those methods directly to the String class.
 - "Under the hood" Kotlin still creates these as static functions however, so no actual changes are made to the class you add an extension function to. 
   
```Kotlin
fun replaceMultipleWhiteSpace(value: String): String {
    val regex = Regex("\\s+")
	return regex.replace(value, " ")
}

fun String.replaceMultipleWhiteSpaceExtension(): String {
	val regex = Regex("\\s+")
	return regex.replace(this, " ")
}

val text = "With   Multiple   Whitespace"
println(replaceMultipleWhiteSpace(text)) //Usage of utility function as a static function
println(text.replaceMultipleWhiteSpaceExtension()) //Usage og utility function as an extension function
```

### Infix Functions

 - Member of a class, or an extension function.
 - Has a single parameter.
 - Marked with Infix-keyword
 - Used to make code more readable

```Kotlin
class Header(var Name: String)

infix fun Header.plus(other: Header) : Header {
    return  Header(this.Name + other.Name)
}

val h1 = Header("H1")
val h2 = Header("H2")
val h3 = h1 plus h2 //calling the infix function. This equals calling h1.plus(h2)
println(h3.Name) //H1H2
```

### Tail Recursive Functions

 - Many functional languages support Tail Recursiveness as a way of preventing stack overflow when working with recursive functions.
 - To utilize tail recursivenes, the function needs to have a correct "form", and then be marked with the ***tailrec*** keyword.
 - Doing so makes Kotlin optimize the recursiveness by turning it into a loop instead inside the bytecode, effectively removing the caveats of recursive functions while maintaining the readability they
provide for the programmer.
   

- The [Kotlin Documentation's](https://kotlinlang.org/docs/reference/functions.html) definition of correct form for usage of ***tailrec***:
	- *"To be eligible for the tailrec modifier, a function must call itself as the last operation it performs. You cannot use tail recursion when there is more code after the recursive call, and you 
	  cannot use it within try/catch/finally blocks. Currently, tail recursion is supported by Kotlin for JVM and Kotlin/Native."*
   
```Kotlin
tailrec fun fibonacci(n: Int, a: BigInteger, b: BigInteger): BigInteger { 
	return if (n == 0) b else fibonacci(n - 1, a + b, a)
 }

println(fibonacci(10000, BigInteger("1"), BigInteger("0"))) //results in SO without the usage of tailrec-keyword.
 ```

## 5 Classes and Interfaces

### Interfaces

- Interfaces work mostly as they do in java.
- To implement an interface, use the colon-operator on the member implementing the interface (see class NorwegianTime below).
- Kotlin does not use @Override-annotation to specify overriding of implemented functions, but the override keyword.
- When implementing two interfaces with a similar default function, you can choose which implementation you want in your class.

````Kotlin
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
````

### Classes Basics

 - Like interfaces, classes in Kotlin works mostly in the same way as in Java
 	- Differences are mostly syntactical.
 - Classes in Kotlin are by default final, meaning they can't be extended unless explicitly marked with the ***open*** keyword.
   	- Abstract classes are by default open
 	- This is also applies to class methods.

```Kotlin
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
```

### Sealed Classes

 - Used to restrict class hierarchies.
 - Often referred to as "Enums on steroids".
 - Defines a restricted set og derived classes.
 - For a more detailed explanation and some use cases, take a look at [Baeldung's article](https://www.baeldung.com/kotlin/sealed-classes) on the subject


````Kotlin
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
````