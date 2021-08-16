# Learning Kotlin - Notes

## Table of Contents

More important than course notes, the **[Kotlin documentation](https://kotlinlang.org/api/latest/jvm/stdlib/)**

0. [Compilation](#0-compilation)
1. [General](#1-general)
 - 1.1 [Exceptions](#exceptions)
 - 1.2 [Type Aliases](#type-aliases)
 - 1.3 [Equality](#equality)
 - 1.4 [Casting and Typechecking](#casting-and-typechecking)
 - 1.5 [String Templates](#string-templates)
 - 1.6 [Raw Strings](#raw-strings)
2. [Statements](#2-statements)
 - 2.1 [Statements as Expressions](#statements-as-expressions)  
 - 2.2 [When Statement](#when-statement)
3. [Datatypes and Null Reference Handling](#3-loops)
 - 3.1 [Datatypes](#data-types)  
 - 3.2 [Arrays](#arrays)
 - 3.3 [Null References](#null-references)

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

 - Typechecking is done using the "is" keyword, rather than "instanceof" as you do in Java.
"is" can be reversed with "!is" (not is).

- Casting is done using the "as" keyword.

 - Note that Kotlin has a notion of smart-casting, whereas if a value has been checked using "is", the value is treated as
it has already been cast. 

```Kotlin
val something: Any = "Hello There"
if (something is String) {
    val newString = something as String //redundant manual casting due to smart-casting
}
```

### String Templates

 - Whenever the value of a variable is needed within a string, the $-operator can be used instead of wrapping the value in
a String.valueOf() or calling the values' .toString().

 - This can also be used to substitute in expressions.

````Kotlin
override fun toString(): String {
	return "Employee(name=$name, id=$id"
}

val numerator = 10
val denominator = 20
println("The value of $numerator divided by $denominator is ${numerator/denominator}")
````

### Raw Strings

 - Raw Strings, or tripple-quoted Strings, are used to avoid having to escape characters and to get a String on the same
format as specified (i.e. if you put a new line in the String, it will be printed like you had put a \n-operator there in Java)

 - The trimMargin()-function can be used to trim away everything up to and including the provided character on each new line.
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

 - Kotlin is built on Java, and so it has the same basic datatypes as Java, most of them also functions in the same way as in Java.

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

 - Arrays in Kotlin can also be instantiated without specifying their types, but it is possible to do so.
To initialise an empty array, the datatype has to be specified

````Kotlin
val names = arrayOf("Fredrik", "Thomas", "Jokke")
val names2 = arrayOf<String>("Fredrik", "Thomas", "Jokke")
val names3 = Array<String>
````

 - Retrieving elements from arrays can be done using the index

````Kotlin
val names = arrayOf("Fredrik", "Thomas", "Jokke")
println(names[0]) //Fredrik
````

 - Initialising arrays with values can also be done using lamdba expressions

````Kotlin
val evenNumbers = Array(16) {i -> i * 2} //Array with 16 elements. For each index, set the index's element to be the index multiplied by two
````

 - Kotlin also allows arrays with mixed data types. It's datatype will then be inferred to Any.

````Kotlin
val mixedArray = arrayOf("hello", 22, 11L, 'a')
````

 - To pass Kotlin arrays to Java methods taking in an array as an argument, the Array has to be initialised using a primitive
type array.

Assume we have the following Java code:
````Java
class SomeClass {
    
    public void printNumbers(final int[] numbers) {
        for(int number: numbers) {
			System.out.println(number);
		}
	}
}
````

To call it in Kotlin we would need to do one of the following to not get a type-inference error:
````Kotlin
val numbers = intArrayOf(1, 2, 3, 4)
SomeClass().printNumbers(numbers)

val moreNumbers = arrayOf(1, 2, 3, 4)
SomeClass().printNumbers(moreNumbers.toIntArray())
````

### Null References

 - Kotlin is designed to make NullPointerExceptions hard to produce. In order to be even allowed to have
a null value, a variables datatype will have to be given postfixed with the ?-operator.

````Kotlin
val number: Int = null //Compilation error, value not nullable
val nullableNumber : Int? = null
````

 - In the case where you have a nullable type, you will not be able to access the dataclass' methods before a null-check has
been performed. There is also a shorthand version of a null-check using the ?-operator.

 - The safe call (shorthand) version works like this: If the value is null, the expression is evaluated to null instead of trying to call the 
following method invocation on the null-object and producing a NullPointerException. Safe calls can also be chained multiple
times, where the entire expression will be evaluated to null if one of the calls fails (is null).

`````Kotlin
val str: String? = "This is not null"

if (str != null) {
    str.uppercase() //Not allowed to call uppercase() on str before a null-check has  been performed.
}

str?.uppercase() //Shorthand version
`````

 - In the case where a nullable variable/value is going to be assigned to another variable/value, the Elvis-operator
(?:) can be utilised to specify a default value for the assignment in case the nullable value is null.
 - The Elvis-operator can also be used at the end of a chain of safe-calls.

````Kotlin
val str: String? = null
val str2 = str ?: "This is the default value"
````


