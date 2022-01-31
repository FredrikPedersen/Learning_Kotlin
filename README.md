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
- 3.4 [Arrays and Null References](#arrays-and-null-references)

4. [OOP in Kotlin: Classes, Functions and Inheritance](#4-oop-in-kotlin-classes-functions-and-inheritance)

- 4.1 [Visibility Modifiers](#kotlins-visibility-modifiers-access-modifiers)
- 4.2 [Classes](#classes)
- 4.3 [Properties and Backing Fields](#properties-and-backing-fields)
- 4.4 [Constants and Data Classes](#constants-and-data-classes)
- 4.5 [Function Basics](#functions)
- 4.6 [Extension Functions](#extension-functions)
- 4.7 [Inheritance](#inheritance)
- 4.8 [Interfaces](#interfaces)
- 4.9 [Singletons](#singletons)
- 4.10 [Companion Objects](#companion-objects)
- 4.11 [Enums](#enums)
- 4.12 [Imports](#imports)

5. [Loops, and the If, When, and Try/Catch Expressions](#5-loops-and-the-if-when-and-trycatch-expressions)

 - 5.1 [For-loop and Ranges](#for-loop-and-ranges)
 - 5.2 [The If Expression](#the-if-expression)
 - 5.3 [The When Expression](#the-when-expression)
 - 5.4 [The Try/Catch Expression](#the-trycatch-expression)

6. [Lambda Expressions, Collections and Generics](#6-lambda-expressions-collections-and-generics)

 - 6.1 [Lambda Expressions](#lambda-expressions)
 - 6.2 [Lists](#lists)
 - 6.3 [Collections Functions](#collections-functions)
 - 6.4 [Maps and Destructuring](#maps-and-destructuring-declarations)
 - 6.5 [Sets](#sets)
 - 6.6 [More Collections Functions](#more-collections-functions)
 - 6.7 [Sequences](#sequences)

## 0 Compilation

When Kotlin code is compiled, the Kotlin Compiler (kotlinc) takes files with the .kt extension as input and generates
bytecode as .class files. At this point, the .class files are equivalent to Java .class files, and the JVM can run them.

When running Kotlin applications, you need the Kotlin Runtime Library (KRL) in addition to the JRE. When distributing a
Kotlin application, you have to distribute both the KRL and the JRE.

Remember that when writing Kotlin, the language is for all intents and purposes just a syntactical sugar for Java.
Whether you write strictly non-verbose code "the Kotlin way" or take more verbose approaches to writing it, it will very
likely not affect application performance in any way. In the end it is all compiled to the same bytecode as it would be
in Java anyways. Most of the best practices in Kotlin are meant for you as a developer to have easy to read code.

## 1 General

- Var is for mutable variables.
- Val is for immutable values. Always declare val and change to var if needed later (immutability is encouraged).
- Public access modifier is default behaviour in Kotlin when nothing else is specified.
    - Package private modifier does not exist in Kotlin.
    - Does however have [***
      internal*** access modifier](https://kotlinlang.org/docs/reference/visibility-modifiers.html), which scopes the
      visibility to the member's module.
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

- In Kotlin it is not required to rethrow nor catch exceptions from classes throwing exceptions, due to the usage of
  unchecked exceptions.
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

- Note that Kotlin has a notion of smart-casting, whereas if a value has been checked using "is", the value is treated
  as it has already been cast.

```Kotlin
val something: Any = "Hello There"
if (something is String) {
    val newString = something as String //redundant manual casting due to smart-casting
}
```

### String Templates

- Whenever the value of a variable is needed within a string, the $-operator can be used instead of wrapping the value
  in a String.valueOf() or calling the values' .toString().

- This can also be used to substitute in expressions.

````Kotlin
override fun toString(): String {
    return "Employee(name=$name, id=$id"
}

val numerator = 10
val denominator = 20
println("The value of $numerator divided by $denominator is ${numerator / denominator}")
````

### Raw Strings

- Raw Strings, or tripple-quoted Strings, are used to avoid having to escape characters and to get a String on the same
  format as specified (i.e. if you put a new line in the String, it will be printed like you had put a \n-operator there
  in Java)

- The trimMargin()-function can be used to trim away everything up to and including the provided character on each new
  line. Default character if no character is passed to trimMargin is |.

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

    return when (answer) {
        correctAnswer -> message + "correct"
        null -> "You haven't answered yet!"
        else -> message + "wrong"
    }
}
```

## 3 Data Types and Null Reference Handling

### Data Types

- Kotlin is built on Java, and so it has the same basic datatypes as Java, most of them also functions in the same way
  as in Java.

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

- Any is the root of the Kotlin class hierarchy, with every class having Any as it's superclass, similar to Object in
  Java.
- Unit is the Kotlin equivalent of the void type in Java.
- Nothing has no instances. You can use Nothing to represent "a value that never exists": for example, if a function has
  the return type of Nothing, it means that it never returns (always throws an exception).

### Arrays

- Arrays in Kotlin can also be instantiated without specifying their types, but it is possible to do so. To initialise
  an empty array, the datatype has to be specified

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
val evenNumbers =
    Array(16) { i -> i * 2 } //Array with 16 elements. For each index, set the index's element to be the index multiplied by two
````

- Kotlin also allows arrays with mixed data types. It's datatype will then be inferred to Any.

````Kotlin
val mixedArray = arrayOf("hello", 22, 11L, 'a')
````

- To pass Kotlin arrays to Java methods taking in an array as an argument, the Array has to be initialised using a
  primitive type array.

Assume we have the following Java code:

````Java
class SomeClass {

    public void printNumbers(final int[] numbers) {
        for (int number : numbers) {
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

- Kotlin is designed to make NullPointerExceptions hard to produce. In order to be even allowed to have a null value, a
  variables datatype will have to be given postfixed with the ?-operator.

````Kotlin
val number: Int = null //Compilation error, value not nullable
val nullableNumber: Int? = null //Allows type to be nullable
````

- In the case where you have a nullable type, you will not be able to access the dataclass' methods before a null-check
  has been performed. There is also a shorthand version of a null-check using the ?-operator.

- The safe call (shorthand) version works like this: If the value is null, the expression is evaluated to null instead
  of trying to call the following method invocation on the null-object and producing a NullPointerException. Safe calls
  can also be chained multiple times, where the entire expression will be evaluated to null if one of the calls fails (
  is null).

`````Kotlin
val str: String? = "This is not null"

if (str != null) {
    str.uppercase() //Not allowed to call uppercase() on str before a null-check has been performed when type is nullable
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

- If you are absolutely sure a value cannot be null, the not-null assertion operator (!!) can be used to skip the
  not-null assertion. If the value is null, a NullPointerException will be thrown.

````Kotlin
val str: String? = "This is not null"
str!!.uppercase() //no problem

val str2: String? = null
str2!!.uppercase() //NPE
````

- In the case of functions expecting non-null values as parameters, the let-operator allows us to make a check for null
  values, not calling the function if the value is null.

````Kotlin
val str: String? = "Not null"
printText(str) //Not allowed due to str being nullable.
str?.let { printText(it) } //Won't call the function if str == null.

fun printText(message: String) {
    println(message)
}
 ````

- When comparing nullable and non-nullable variables, be aware that the equals function in Kotlin is a safe-operator.

 ````Kotlin
val str: String? = null
val str2: String = "Not null"

println(str == str2) //Allowed due to == being an under the covers safe operator.
 ````

### Arrays and Null References

- You can initialize an array of a specific type with only null-values using the arrayOfNulls<T>(size: Int)-function.

````Kotlin
val nullableInts = arrayOfNulls<Int>(5) //array of ints with five null-values
````

## 4 OOP in Kotlin: Classes, Functions and Inheritance

### Kotlin's Visibility Modifiers (Access Modifiers)

| Access Modifier | Kotlin | Java |
| --------------- | ------ | ---- |
| Private | Visiblle withing the same file | Can't be used |
| Protected | Can't be used | Can't be used |
| Public | Visible everywhere | Visible everywhere |
| Internal | Visible within the same module | N/A |

- Note: This table is **only** valid for **top-level** items
- Top level items are public by default, in contrast to Java's top-level default; package-private.

### Classes

- All classes are public and final by default.
- There are multiple ways to declare classes in Kotlin varying in terms of verbosity but all equal in functionality.

````Kotlin
//Using an init-block
class Employee constructor(firstName: String) {

    val firstName: String

    init {
        this.firstName = firstName
    }

}

//Initializing properties when declared
class Employee constructor(firstName: String) {

    val firstName: String = firstName
}

//Initializing and declaring the properties in the constructor (access modifiers for constructors are placed here)
class Employee private constructor(val firstName: String)

//Initializing and declaring the properties in the constructor, without the constructor keyword
class Employee(val firstName: String)

//A class without a primary constructor
class Employee {

    val firstName: String

    constructor(firstName: String) {
        this.firstName = firstName
    }

}
````

- When needing multiple constructors, there are also multiple ways of doing this

````Kotlin
//The more verbose/Java-like way
class Employee(val firstName: String) {

    var fullTime: Boolean = true //Values not assigned in primary constructors must be given a default value

    //Must call primary constructor first to handle firstName
    //Secondary constructors does not declare properties like primary ones does. thus one cannot use val-keyword in secondary constructors 
    constructor(firstName: String, fullTime: Boolean) : this(firstName) {
        this.fullTime = fullTime
    }
}

//The shorthand way. Gives the second argument a default value, making it optional when calling the constructor
//Secondary constructors also accept default values
class Employee(val firstName: String, var fullTime: Boolean = true)

val emp1 = Employee("Fredrik") //fullTime defaults to true.
val emp2 = Employee("Michael", false)
````

### Properties and Backing Fields

- When declaring public properties for a class, getters and setters are automatically generated and available by using
  dot-notations.
- Note that vals don't get setters generated, as they are final.
- Kotlin's rule for properties is that getters and setters have the same visibility as the properties, or a more
  restrictive visibility.
- Due to how the dot-notation uses the getters and setters under the covers, the properties are not directly accessed
  even when public, and therefore private properties are only necessary if they are only supposed to be used inside the
  class.
- If one needs to do something more specific in getter and setters than the standard generated methods provide, one has
  to declare the properties inside the class and not in a primary constructor.

````Kotlin
class Employee(val firstName: String, var fullTime: Boolean = true) {}

val emp = Employee("Fredrik")
println(emp.firstName) //Using the getter via the dot-notation
emp.fullTime = false //Using the setter via the dot-notation.

//Example using custom getters and setters for fullTime
class Employee(val firstName: String, fullTime: Boolean = true) {

    var fullTime = fullTime
        get() {
            println("Running custom get")
            return field
        }
        set(value) {
            println("Running custom set")
            field = value
        }
}
````

### Constants and Data Classes

- Constants can be declared at top-level outside of classes

````Kotlin
val MY_CONSTANT = 100

fun main(args: Array<String>) {
    println(MY_CONSTANT)
}
````

- Kotlin has a special type of class called a data class, used for objects meant to store state and nothing else.
- They are declared by using the keyword data before class.
- Data classes come with toString, equals, hashCode, and copy function implementations (which can all be overriden
  manually).
- You can declare properties inside the data class (as in not in he primary constructor), but they will not be included
  in the generated functions.
- Data classes have a few requirements:
    1. The primary constructor has to have at least one parameter
    2. All the primary constructor parameters has to be marked var or val
    3. Data classes cannot be abstract, sealed or inner classes

````Kotlin
data class Car(val color: String, val model: String, val year: Int) {}

val car = Car("someCar", "someModel", 1994)
val car2 = Car("otherCar", "otherModel", 1997)
val car3 = car.copy()
val car4 = car.copy(year = 2022) //Specific properties can be overridden at will in the copy function

println(car)
println(car.equals(car2))
println(car.hashCode())
````

### Functions

- Functions are declared with the following Syntax: *fun \<name>(\<arguments>): \<returnType> {}*
- The default return type for Kotlin functions are Unit.
- There is also a shorthand version without brackets for functions that only returns the result of an expression.
- The curly bracket version are called block-body functions, while the shorthand versions are called expression
  functions.
- Functions can be called using named arguments, where you specify which parameter is given what value, and in an order
  of your choosing.
- Member functions (methods in Java-lingo) are called just like in Java, by instantiating an object of a class and then
  calling *classObject.method()*
- In Kotlin, functions are first-class citizens, meaning they can be stored in variables and data structures, and can be passed as arguments to and returned from other higher-order functions. I.e. you can perform any operation on function that are possible for other non-function values.
  - This is not covered in the course, so see the [Kotlin Docs](#https://kotlinlang.org/docs/lambdas.html)

`````Kotlin
//Full syntax
fun multiply(multiplicand: Int, multiplier: Int): Int {
    return multiplicand * multiplier
}

//Shorthand version, with the return type inferred by the compiler
fun multiply(multiplicand: Int, multiplier: Int) = multiplicand * multiplier

//Calling the function "normally"
multiply(3, 4)

//Calling the function using named arguments
multiply(multiplier = 12, multiplicand = 5)
`````

- Functions with variable amount of arguments can be declared using the varargs keyword.
- Like with Java's ...-operator for varargs, the varargs operator creates an Array of the specified argument type.
- There can only be one vararg parameter in a function signature.
- The vararg argument does not have to be the last argument due to named parameters, but if it isn't the last argument,
  the method HAS to be called using named parameters.
- Unlike Java, Kotlin's varargs does not accept arrays as argument types. The spread-operator (*) comes in handy if one
  needs to pass an array into a function with a vararg parameter by unpacking the array.

````Kotlin
//varargs creates an array of integers in this case
fun add(vararg numbers: Int, label: String) {
    var result = 0

    for (number in numbers) {
        result += number
    }

    println("$label $result")
}

val numbers = arrayOf(1, 2, 3)

add(1, 2, 3, label = "Result:")
add(1, 2, 3, 4, 5, 6, label = "Result:")
add(*numbers, label = "Result:")
````

### Extension Functions

- Extension functions is a functionality in Kotlin allowing you to seemingly add functions to existing classes.
- This can be done by declaring a function with the following syntax: *fun \<class>.\<name>(\<arguments>): \<returnType>
  {}*
- The function's body can then be written as if a proper member of the class it is extending, i.e. using this-keyword to
  work with an instance of the class.
    - Public properties of the class can also be referenced by name inside the function body.

````Kotlin
//Adding capitalize function to the String class
fun String.capitalize(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}

val lowercaseString = "hello there"
println(lowercaseString.capitalize()) //Hello There
````

### Inheritance

- All classes in Kotlin are by default public and final, with them being final meaning they can't be extended unless
  explicitly marked as non-final. For this, we have the open-keyword.
- When working with abstract classes, the open keyword is not necessary, as abstract classes must be extended to be
  utilized, thus they are not final by default.
- When overriding functions from a superclass, the function must be marked with the open keyword in the superclass, and
  the override keyword in the subclass.
- Functions, like classes, does not need to be marked with the open keyword if they are abstract.
- If a method has been overridden by a subclass, and you explicitly don't want it to be overridden by further
  subclasses, it can be marked with the final keyword.
- Data classes cannot be extended.

````Kotlin
abstract class Printer(val modelName: String) {

    open fun printModel() = println("Printer model is $modelName")
    abstract fun bestSellingPrice(): Double
}

class LaserPrinter(modelName: String) : Printer(modelName) {

    final override fun printModel() = println("Laser printer model is $modelName") //Cannot be overridden by subsequent sub-classes
    override fun bestSellingPrice(): Double = 1299.00
}
````

### Interfaces

- Interfaces are functionally the same as in Java.
- Interfaces are extendable by default.
- Kotlin interfaces can define properties.

````Kotlin
interface MyInterface {

    val number: Int

    val number2: Int
        get() = 117 //Need a getter to define a specific value, can still be overridden

    fun myFunction(str: String): String
}

interface MySubInterface : MyInterface {

    fun mySubFunction(num: Int): String
}

class MyClass : MySubInterface {

    override val number: Int = 3

    override fun myFunction(str: String): String {
        return str
    }

    override fun mySubFunction(num: Int): String {
        return num.toString()
    }
}
````

### Singletons

- The object-keyword in Kotlin is used to create objects of anonymous classes that can be used one time.
- The object keyword is also useful for creating singleton classes.
- The instance of an object is created the first time the object is used.

````Kotlin
object CompanyCommunications {

  val currentYear = Year.now().value

  fun getTagLine() = "Our Company Rocks!"
  fun getCopyrightLine() = "Copyright \u00A9 $currentYear Our Company. All rights Reserved"
}

println(CompanyCommunications.getCopyrightLine())
````

### Companion Objects

- Companion objects allows you to access members of a class without instantiating the class, similar to the behaviour of static in Java.
- Only one Companion object is allowed per class.
- Naming the companion is optional.

````Kotlin
class SomeClass {
  
  companion object SomeCompanion {
    private var privateVar = 6

    fun accessPrivateVar() = "I am accessing privateVar $privateVar"
  }
}

println(SomeClass.accessPrivateVar())
````

- A frequent use case for companion objects is to implement a factory pattern as they allow you to call private constructors.

````Kotlin
class FactoryClass private constructor(val someString: String) {

  companion object {

    fun create(str: String) = FactoryClass(str)

    fun create(str: String, lowerCase: Boolean): FactoryClass {
      return if (lowerCase) {
        FactoryClass(str.lowercase())
      } else {
        FactoryClass(str.uppercase())
      }
    }
  }
}

val factoryMade1 = FactoryClass.create("I was made by a factory")
val factoryMade2 = FactoryClass.create("I was also made by a factory and am really intense about it", true)
````

### Enums

 - Enums in Kotlin are very similar to Enums in Java.

````Kotlin
enum class Department(val fullName: String, val numEmployees: Int) {
  
  HR("Human Resources", 5),
  IT("Information Technology", 10),
  ACCOUNTING("Accounting", 3),
  SALES("Sales", 20);

  fun getDeptInfo() = "The $fullName department has $numEmployees employees"
}

println(Department.IT.getDeptInfo())
````

### Imports

- Syntax for importing classes and interfaces is equal to how it is done in Java.
- A top level function is also imported in the same way.
- Kotlin allows for import aliases e.g. using the as-keyword after an import statement followed by the alias it should be given.

## 5 Loops, and the If, When and Try/Catch Expressions

- Note: The while and do-while loop is not covered due to having the same syntax and behaviour as in Java.

### For-loop and Ranges

 - The traditional for-loop is not available in Kotlin.
 - For-loops in Kotlin uses ranges.
   - "A range defines a closed interval in the mathematical sense: it is defined by its two endpoint values which are both included in the range" - Kotlin Docs.
   - Ranges and Progressions are explained best in the [Kotlin Docs](https://kotlinlang.org/docs/ranges.html#progression)
 - To create a range, use the rangeTo function, or it's shorthand, the ..-operator.
 - A reverse range is created using the downTo-function.
 - The for-loop uses the syntax: *for(\<variableName> in \<range>) {}*.

````Java
//This type of for-loop is not available in Kotlin
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
````
````Kotlin
//Demonstration of ranges
val intRange = 1.rangeTo(20)
val charRange = 'a' .. 'z'
val stringRange = "XYZ".downTo("ABD")

println(3 in intRange) //true
println(10 in intRange) //false
println('q' in charRange) //true
println("CCC" in stringRange) //true

//Iterating over ranges
for (i in intRange) {
    println(i)
}

//Does 4 steps per iteration 
for (i in intRange step 4) {
    println(i) //1, 5, 9, 13, 17
}

//Can also loop backwards
for (i in 20 downTo 15) {
    println(i)
}

//Exclude the end-value with the until-keyword
for (i in 1 until 10) {
    println(i) //1..9
}

//Does not work due to String Ranges not being iterable
for (s in stringRange) {
    println(s)
}
````

 - Looping through arrays uses the same syntax as ranges
 - The in-operator can also be used to check for values in a range or an array without writing a loop.

````Kotlin
val seasons = arrayOf("Spring", "Summer", "Winter", "Fall")

for (season in seasons) {
    println(season)
}

//One can also iterate through an array using indexes
for (index in seasons.indices) { 
    println("${seasons[index]} is season number $index}")
}

//The forEach loop also exists in Kotlin and utilizes lambda expressions
seasons.forEach { println(it) }
seasons.forEachIndexed {index, value -> println("$value is season $index")}

val isNotASeason = "whatever" !in seasons //true
val containsE = 'e' in "Hello" //True

````

 - Loops can also be given names which can be referenced with break and continue statements.
 - It is done using the syntax \<loopName>@ \<loop expression>.

````Kotlin
//Breaks out of k and j loop when k = 7
for (i in 1..3) {
    println("i = $i")
  
    jloop@ for (j in 1..4) {
        println("j = $j")
      
        for (k in 5..10) {
            println("k = $k")
            
            if (k == 7) {
                break@jloop
            }
        }
    }
}
````

### The If Expression

- Contrary to Java, If-statements also functions as expressions, being able to store the statement's value.
- The shorthand ternary operator from Java does NOT exist in Kotlin.
- If used as an expression, there must always be an else-branch, as the expression must return something if the condition is not met.

````Kotlin
//Using the If-statement as expression
val num: Int = if (someCondition) 420 else 69

val num2 : Int = if (someCondition) { 
    420 
} else { 
    69
}
````

### The When Expression

 - The when-expressions is essentially Java's switch-expression on steriods.
 - There is no break statement in Kotlin, it is done under the covers in each when-case, meaning fall-through cases are not possible in Kotlin.
 - Multiple cases are allowed on one line.
 - Ranges are allowed as cases.
 - Expressions are allowed as cases.
 - Class types are allowed as cases.
 - Enums are allowed as cases.
 - Since when is an expression, it can return values to be stored in variables.
   - Like the If-expressions, this requires an else-case.
 - There can also be Switch-expressions without a specific value to compare each case against.

`````Kotlin
val num = 200
val x = 20

//Equals a Java Switch/Case with a case for 100, 200, 300 and default, with a break in each of them.
when (num) {
    100, 600 -> println("100 or 600") //Multiple cases in one line
    in 200..299 -> println("In range 200 to 299") //Using a range as a case
    x + 300 -> println("Bigger than 300") //Using an expression as a case
    else -> println("Does not match anything")
}

val unknownType: Any = "I am a String"

//Checking for a specific type, returning an integer-value for each case
val y: Int = when (unknownType) {
    is String -> 1
    is BigDecimal -> 2
    is Int -> 3
    else -> -1
}

//Without a value to compare each case to
when {
    num > x -> println("$num is bigger than $x")
    num < x -> println("$num is smaller than $x")
    else -> println("$num and $x are alike")
}
`````

### The Try/Catch Expression

- The try/catch syntax is similar to that in Java, and is used for the same purposes.
  - Note: Kotlin does not make a distinction between checked and unchecked exceptions.
- Like If and When, Try/Catch is an expression which can return a value.
  - The finally-block does not return any value.
- There is no equivalent of try-with-resources in Kotlin, instead there is an extension method called use, which is applicable to all AutoCloseable and Closeable objects.
  - See [Baeldung's Article](https://www.baeldung.com/kotlin/try-with-resources) for more on the use-method

````Kotlin
val x: Int = try {
    Integer.parseInt("3")
} catch (e: NumberFormatException) {
    -1
} finally {
    println("I'm in the finally block, and I do not return any values")
    -2 //This return value is ignored when the expression is evaluated
}
````

## 6 Lambda Expressions, Collections and Generics

- Kotlin has a couple of it's own collection classes, but mostly uses the Java collections with some added convenience functions.
- There are also some enhancements to lambda expressions.

- For some of the examples in this chapter, assume we have the following Employee class and list of Employee objects available: 

````Kotlin
data class Employee (val firstName: String, val lastName: String, val startYear: Int) {}

val employees = listOf(
  Employee("Fredrik", "Pedersen", 2022),
  Employee("Thomas", "Kristiansen", 2022),
  Employee("Joakim", "Standal", 2019),
  Employee("Andreas", "Rinvoll", 2023)
)
````

### Lambda Expressions

 - The lambda syntax is mostly the same as in Java.
 - Lambdas has to be withing curly-braces.
 - Lambdas can be stored in variables.
 - Lambdas can be called directly using the run-function.
 - When a lambda expression is the last parameter for a function, it can be passed without using parenthesis for the function (see example with minBy-functiion below). This is known as trailing lambdas.
 - When there is only one possible argument for the lambda expressions, the it-keyword can be utilized.
 - Member references are, like in Java, also allowed.
 - Using top level functions are allowed, but they cannot take any arguments.
 - Unlike Java, lambdas in Kotlin can access non-final local variables, but they have to be declared before the lambda-expression.

````Kotlin
fun topLevelFunction() = println("I'm a top-level function!")

// A lambda expression executed using the run-function.
run { println("This is a lambda function!") }

//A top-level function run with a lambda
run {::topLevelFunction}

//Passing a lambda expression to the minBy-function
println(employees.minBy { emp -> e.startYear })

//The same expression, but using the it-keyword
println(employees.minBy { it.startYear })

//Again, but using a member-reference
println(employees.minBy { Employee::startYear })
````

- Using a lambda expression with a receiver is a handy way to make code more consise.
  - Note: a receiver object is an object passed to a lambda expression.
- When passing an instance of an object to the with-function, all of the instance's member functions becomes available without directly referencing the instance inside the lambda body.
- Returning the when-function returns the result of the last expression withing it's block.

````Kotlin
//Passing a StringBuilder-instance to the with-function and returning the StringBuilder.
//Note how the append and toString functions of StringBuilder are available without referencing the instance.
fun count(start: Int, end: Int): String {
  return with(StringBuilder()) {
    for (i in start until end) {
      append(i.toString())
      append(", ")
    }

    append(end)
    toString()
  }
}

//Same method without using the with-function for reference.
fun count(start: Int, end: Int): String {
  val numbers = StringBuilder()
  
  for (i in start until end) { 
    numbers.append(i)
    numbers.append(", ")
  }
  
  numbers.append(end)
  return numbers.toString()
}
````

- The apply-function works mostly the same as the with-function, but it is applied on an instance rather than passing the instance to the function.
- The apply-function returns the instance it is applied to.

````Kotlin
//Same function as previous example, but with apply() instead of with()
fun count(start: Int, end: Int): String {
  return StringBuilder().apply {
    for (i in start until end) {
      append(i.toString())
      append(", ")
    }

    append(end)
  }.toString()
}
````

- Labels comes in handy if performing operations inside nested apply() or with()-calls

````Kotlin
"Some String".apply someString@ { 
    "Another String".apply { 
        println(lowerCase()) //Applies on Another String
        println(this@someString.uppercase()) //Applies on Some String
    }
}
````

- Returning values from an inline lambda will return a value for entire function the lambda is invoked from.
  - This is called a non-local return.
- The non-local return behaviour can be overridden by labelling the lambda

````Kotlin
//Non local-return, ends the method execution if an employee with lastName is found
fun findByLastName(employees: List<Employee>, lastName: String) {
  employees.forEach {
    if (it.lastName == lastName) {
      print("Found employee with lastname $lastName")
      return
    }
  }

  println("Found no employee with lastname $lastName")
}

//Local return will execute both println()-invocations.
fun findByLastName(employees: List<Employee>, lastName: String) {
  employees.forEach labeledLambda@{
    if (it.lastName == lastName) {
      print("Found employee with lastname $lastName")
      return@labeledLambda
    }
  }

  println("Found no employee with lastname $lastName")
}
````

### Lists

- When working with Collections, Kotlin makes a distinction between mutable and immutable collections.
- The standard lists are immutable and is an implementation of *java.util.Arrays$ArrayList*.
- Lists in Kotlin allows the usage of member functions and square-bracket annotations to perform operations on the list's contents

````Kotlin
val immutable = listOf("Hello", "There") //Default immutable list, java.util.Arrays$ArrayList
val emptyList = emptyList<String>() //kotlin.collections.EmptyList
val notNull = listOfNotNull("Hello", null, "There") //Removes any null values, java.util.Arrays$ArrayList
val arrayList = arrayListOf("Hello", "There") //A mutable ArrayList, java.util.ArrayList 
val mutable = mutableListOf("Hello", "There") //A mutable List, java.util.ArrayList

//Converting an array to a list
val array = arrayOf("Hello", "There")
val spreadList = listOf(*array) //Using spread-operator
val functionList = array.toList() //Using member-function of array-class

//Using square-bracket annotations and member functions to change and retrieve values from a mutable list
mutable[1] = "Changed"
mutable.set(1, "Changed Again")

println(mutable[1])
println(mutable.get(1))
````

### Collections Functions

- Some handy functions when working with Kotlin Collections:
  - List.last() - Returns the last element in the list.
  - List.first() - Returns the first element in the list.
  - List.asReversed() - Returns a reversed version of the list the function is invoked on.
  - List.getOrNull(index: Int) - Returns an element at the given index or null if it does not exist.
  - List.maxOrNull() - Returns the largest element, or null if there are no elements.
  - Iterable.zip(other: Iterable\<R>) - Returns a combined version of the passed in Iterable and the one the zip-function is invoked on.
    - The resulting list consists of kotlin.Pair instances, where elements having the same index in the different collections are grouped into a pair.
  - Lists can be concatenated like Strings.
  - Iterable.union(other: Iterable\<R>) - Returns a combined version of the two iterables without any duplicates,
  - Iterable.distinct() - Returns the iterable without any duplicates.
  - Collection.toMutableList() - Returns a mutable list of the collection the method is invoked on.

````Kotlin
val strings = listOf("Hello", "There", "Hello", "There")
val moreStrings = listOf("I", "Am", "The", "Senate")

val kenobi = strings.last() //"There"
val hello = strings.first() //"Hello"
val reversed = strings.asReversed() //["There", "Hello", "There", "Hello"]
val willBeNull = strings.getOrNull(5) //Null
val maxValue = strings.maxOrNull() //"There"
val combined = strings.zip(moreStrings) //[("Hello", "I"), ("There", "Am"), ("Hello", "The"), ("There", "Senate")]
val concatenated = strings + moreStrings //["Hello", "There", "Hello", "There", "I", "Am", "The", "Senate"]
val union = strings.union(moreStrings) //["Hello", "There", "I", "Am", "The", "Senate"]
val noDuplicates = strings.distinct() //["Hello", "There]
val mutable = strings.toMutableList()
````

### Maps and Destructuring Declarations

- Maps themselves does not differ from how they function in Java.
- The standard Map in Kotlin is an implementation of java.util.LinkedHashMap, and is immutable.
- The mutable version is also an implementation of the same Java-class.
- If you don't want a LinkedHashMap, you can specify what type of map you want by calling the appropriate *MapOf()*-function.
  - E.g. *hashMapOf()* or *linkedMapOf()*
- Destructuring is to take values in a collection and distributing them to separate variables.
  - To be able to destructure a class, one must implement component functions. 
  - Note that data classes gets the component functions out of the box.

````Kotlin
//Manually adding component functions to allow destructuring
class Employee (val firstName: String, val lastName: String, val startYear: Int) { 
  operator fun component1() = firstName
  operator fun component2() = lastName
  operator fun component3() = startYear
}

val employee1 = Employee("Fredrik", "Pedersen", 2022)
val employee2 = Employee("Thomas", "Kristiansen", 2022)

val immutableMap = mapOf(
  1 to employee1,
  2 to employee2
)

//Destructuring the map's values
for ((key, value) in immutableMap) {
    //doSomething
}

//Destructuring an Employee object
val (firstName, lastName, yearStarted) = employee1
````

### Sets

- Instantiated in the same way as lists and maps using the setOf-function.
- Is an implementation of java.util.LinkedHashSet.
- Some Kotlin functions for sets:
  - Set.plus(element: T) 
    - Returns a new set containing the invoked Set-object plus element. Returns only the invoked Set-object if element is a duplicate of an already existing value.
  - Set.minus(element: T) 
    - Same functionality as plus, but removes element.
  - Iterable.drop(n: Int) 
    - Returns a new set without the values at the first n indexes in the set.
  - Iterable.average() 
    - Returns a double with the average value of the set, only available for Iterables with types extending Number.
- Note that none of the above functions directly modifies the set the function is invoked upon, even if it is a MutableSet.

````Kotlin
val intSet = setOf(10, 15, 19, 4, 3)
val plusSet = intSet.plus(100) //[10, 15, 19, 5, 3, 100] 
val minusSet = intSet.minus(5) //[10, 15, 19, 3]
val averageValue = intSet.average() //10.4
val droppedSet = intSet.drop(2) //[19, 5, 3, -22]
````

### More Collections Functions

- Iterable.filter(predicate: (T) -> Boolean)
  - Takes in a lambda expression to remove any values not matching the predicate in an Iterable.
- Iterable.map(transform: (T) -> R)
  - Applies the transform-function to each element in the Iterable.
- Iterable.all(predicate: (T) -> Boolean)
  - Verifies if all values in the collection matches the predicate.
- Iterable.count(predicate: (T) -> Boolean)
  - Counts all values matching the predicate.
- Iterable.find(predicate: (T) -> Boolean)
  - Returns the first value matching the predicate.
- Iterable.groupBy(keySelector: (T) -> K)
  - Groups the elements in the Iterable by the given selector.
- Iterable.sortedBy(crossinline selector: (T) -> R?)
  - Sorts the Iterable by the given selector
- All of the above functions returns new instances of the Collections they are invoked upon, so they do not modify them.

````Kotlin
val employeeList = listOf(
  Employee("Fredrik", "Pedersen", 2022),
  Employee("Thomas", "Kristiansen", 2022),
  Employee("Joakim", "Standal", 2019),
)

val filteredMap = employeeList.filter { it.startYear == 2019} //{3=Employee(firstName=Joakim, lastName=Standal, startYear=2019)}
val nameMap = employeeList.map { it.firstName } //["Fredrik", "Thomas", "Joakim"]
val chainedFilterAndMap = employeeList.filter { it.startYear == 2022 }.map { it.firstName } //["Fredrik", "Thomas"]
val isAllStartDuringLastYear = employeeList.all {it.startYear >= 2021} //false
val countThisYear = employeeList.count { it.startYear == 2022 } //2
val found = employeeList.find { it.startYear > 2020 } //Employee(firstName=Fredrik, lastName=Pedersen, startYear=2022)
val sorted = employeeList.sortedBy { it.firstName } //[Employee(firstName=Fredrik, lastName=Pedersen, startYear=2022), Employee(firstName=Joakim, lastName=Standal, startYear=2019), Employee(firstName=Thomas, lastName=Kristiansen, startYear=2022)]
````

### Sequences

- Sequences offer the same functionality as Iterable, but implement another aproach to multi-step Collection processing.
- They are essentially the same as Streams in Java.
- When performing operations on a Collection using sequences, each item in the Collection is evaluated individually per operation.
  - When not using sequences and just chaining function calls, one operation is performed on the entire Collection before returning a new modified Collection and then performing the next operation.
  - This results in one intermediary Collection being created per function call, which is highly cost inefficient and can lead to problems if working with larger Collections. 
- Kotlin Collections are already very efficient, so only use Sequences when working with large collections.
  - Using sequences with smaller collections or when doing few operations may result in performance loss rather.
- [This article](https://typealias.com/guides/when-to-use-sequences/) provides some general guidelines for when to use Sequences vs chaining Collection operations.

````Kotlin
val employeeList = listOf(
  Employee("Fredrik", "Pedersen", 2022),
  Employee("Thomas", "Kristiansen", 2022),
  Employee("Joakim", "Standal", 2019),
)

//First the filter is invoked in the list, returning a new filtered list. Then map is invoked on the filtered list, once again returning a new list.
val chainedFilterAndMap = employeeList.filter { it.startYear == 2022 }.map { it.firstName } //["Fredrik", "Thomas"]

//Converting to a sequence first evaluates each value individually for each step, never returning any intermediary lists.
val sequencedFilterAndMap = employeeList.asSequence().filter { it.startYear == 2022 }.map { it.firstName }.toList() //["Fredrik", "Thomas"]

````
### Generics

- Generics in Kotlin and Java aren't any different in their behaviour, but there are a few extra functionalities in Kotlin.
- The syntax is also the same.
- Multiple upper bounds can be defined using the where-keyword after the function parameter list
  - When defining multiple upper bounds, keep in mind that you can have multiple interfaces, but only one class.
  - When no upper bound is defined, *Any?* is the default upper bound.

````Kotlin
//Example of a function to convert a list containing any type of numbers to integers
fun <T: Number> convertToInt(collection: List<T>): List<Int> {
  val intList = mutableListOf<Int>()

  for (num in collection) {
    intList.add(num.toInt())
  }

  return intList
}

//Function with multiple upper bounds for the generic T
fun <T> append(item1: T, item2: T): Appendable where T: CharSequence, T: Appendable {
  return item1.append(item2)
}
````

 - Inline function parameters can be reified, meaning their type is not erased at runtime.
 - If a function is declared as inline, and its generic parameter as reified, the parameter's type can be checked at runtime.
   - Detailed explanations of inline and reified can be found in this [StackOverflow thread](https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work)
 - The reified keyword only works for inlined functions.
 - An inlined function with reified type is **not** callable from Java code.

````Kotlin
inline fun <reified T> getElementsOfType(list: List<Any>): List<T> { 
  val newList: MutableList<T> = mutableListOf()
  
  for (element in list) {
      if (element is T) {
          newList.add(element)
      }
  }
  
  return newList
}
````