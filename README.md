# Learning Kotlin - Notes

## Table of Contents

More important than course notes, the **[Kotlin documentation](https://kotlinlang.org/api/latest/jvm/stdlib/)**

0. [Compilation](#0-compilation)
1. [General](#1-general)
 - 1.1 [Null Handling](#null-handling)  
 - 1.2 [Exceptions](#exceptions)
 - 1.3 [Type Aliases](#type-aliases)
 - 1.4 [Equality](#equality)
 - 1.5 [Casting and Typechecking](#casting-and-typechecking)
 - 1.6 [String Templates](#string-templates)
 - 1.7 [Raw Strings](#raw-strings)
2. [Statements](#2-statements)
 - 2.1 [Statements as Expressions](#statements-as-expressions)  
 - 2.2 [When Statement](#when-statement)
3. [Datatypes and Null Reference Handling](#3-loops)
 - 3.1 [Datatypes](#data-types)  
 - 3.2 [Arrays](#arrays)

Note! Whenever referencing the Employee class, assume the following POJO class is available:

```Kotlin
class Employee(var name: String, val id: Int) {}
```

## 0 Compilation

When Kotlin code is compiled, the Kotlin Compiler (kotlinc) takes files with the .kt extension as input and generates
bytecode as .class files. At this point, the .class files are equivalent to Java .class files, and the JVM can run them.

When running Kotlin applications, you need the Kotlin Runtime Library (KRL) in addition to the JRE. When distributing a Kotlin
application, you have to distribute both the KRL and the JRE.

Remember that when writing Kotlin, the language is for all intents and purposes just a syntactical sugar for Java. Whether
you write strictly non-verbose code "the Kotlin way" or take more verbose approaches to writing it, it will very likely 
not affect application performance in any way. In the end it is all compiled to the same bytecode as it would be in Java
anyways. Most of the best practices in Kotlin are meant for you as a developer to have easy to read code.

## 1 General

 - Var is for mutable variables.
 - Val is for immutable values. Always declare val and change to var if needed later (immutability is encouraged).
 - Public access modifier is default behaviour in Kotlin when nothing else is specified.  
	- Package private modifier does not exist in Kotlin.
	- Does however have [***internal*** access modifier](https://kotlinlang.org/docs/reference/visibility-modifiers.html), which scopes the visibility to the member's module.
 - Semicolons are not required at the end of statements, but does not produce errors if provided.
 - Bitwise operators (e.g. |, &, ^) are replaced with outright spelling them (e.g. and, or, xor).
	

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
	
### Type Aliases

 - Type aliases provide alternative names for existing types. If the type name is too long you can introduce a different 
   shorter name and use the new one instead.
 - Type aliases are declared at the top level of a Kotlin file


```Kotlin
typealias EmployeeList = List<Employee>

class Employee(var name: String, val id: Int) {}
```

### Equality

 - The == (equals) operator in Kotlin checks for structural equality in Kotlin, not referential equality
 - Referential equality is checked with a === operator. The === operator has a reverse operator in the form of !==

```Kotlin
val employee1 = Employee("Fredrik", 26)
val employee2 = Employee("Thomas", 24)
val employee3 = Employee("Thomas", 24)
val employee4 = employee2

println(employee1 === employee2) //false
println(employee2 === employee3) //false
println(employee1 == employee2) //false
println(employee2 == employee3) //true
println(employee4 === employee2) //true
```
### Casting and Typechecking

Typechecking is done using the "is" keyword, rather than "instanceof" as you do in Java.
"is" can be reversed with "!is" (not is).

Casting is done using the "as" keyword.

Note that Kotlin has a notion of smart-casting, whereas if a value has been checked using "is", the value is treated as
it has already been cast. 

```Kotlin
val something: Any = "Hello There"
if (something is String) {
    val newString = something as String //redundant manual casting due to smart-casting
}
```

### String Templates

Whenever the value of a variable is needed within a string, the $-operator can be used instead of wrapping the value in
a String.valueOf() or calling the values' .toString().

This can also be used to substitute in expressions.

````Kotlin
override fun toString(): String {
	return "Employee(name=$name, id=$id"
}

val numerator = 10
val denominator = 20
println("The value of $numerator divided by $denominator is ${numerator/denominator}")
````

### Raw Strings

Raw Strings, or tripple-quoted Strings, are used to avoid having to escape characters and to get a String on the same
format as specified (i.e. if you put a new line in the String, it will be printed like you had put a \n-operator there in Java)

The trimMargin()-function can be used to trim away everything up to and including the provided character on each new line.
Default character if no character is passed to trimMargin is |.

```Kotlin
//No need to escape backslashes
val filePath = """C:\someDir\someFile.txt"""

// Does not need +-operators when going to a new line. Will be printed as it is declared in regards to new lines.
val longString = """Did you ever hear the Tragedy of Darth Plagueis the wise? 
		|I thought not. It's not a story the Jedi would tell you. It's a Sith legend. Darth Plagueis was a Dark Lord of the Sith,
		|so powerful and so wise he could use the Force to influence the midichlorians to create life...""".trimMargin()
 ```

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
//imagine class Question with string attributes question, answer and correctAnswer
fun solution(): String {
	val message = "Question: $question\nYou answered: $answer, which is "

	return when(answer) {
	    correctAnswer -> message + "correct"
		null -> "You haven't answered yet!"
		else -> message + "wrong"
	}
}
```

## 3 Data Types and Null Reference Handling

### Data Types

Kotlin is built on Java, and so it has the same basic datatypes as Java, most of them also functions in the same way as in Java.

````Kotlin

val myInt = 10
val myLong = 10L
val myByte: Byte = 111 //will be interpreted as an int if datatype is not specified
val myShort: Short = 111 //same as with Byte
val myDouble = 3.14159
val myFloat = 3.14159f //will be interpreted as a double if not specified as a float
val myBoolean = true
````

The new datatypes introduced by Kotlin are Any, Unit and Nothing.
 - Any is the root of the Kotlin class hierarchy, with every class having Any as it's superclass, similar to Object in Java.
 - Unit is the Kotlin equivalent of the void type in Java.
 - Nothing has no instances. You can use Nothing to represent "a value that never exists": for example, if a function has the return type of Nothing, it means that it never returns (always throws an exception).

### Arrays


