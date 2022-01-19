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
- 4.5 [Function Basics](#function-basics)
- 4.6 [Extension Functions](#extension-functions)
- 4.7 [Inheritance](#inheritance)
- 4.8 [Interfaces](#interfaces)
- 4.9 [Singletons](#singletons)
- 4.10 [Companion Objects](#companion-objects)
- 4.11 [Enums](#enums)
- 4.12 [Imports](#imports)

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

### Function Basics

- Functions are declared with the following Syntax: *fun \<name>(\<arguments>): \<returnType> {}*
- The default return type for Kotlin functions are Unit.
- There is also a shorthand version without brackets for functions that only returns the result of an expression.
- The curly bracket version are called block-body functions, while the shorthand versions are called expression
  functions.
- Functions can be called using named arguments, where you specify which parameter is given what value, and in an order
  of your choosing.
- Member functions (methods in Java-lingo) are called just like in Java, by instantiating an object of a class and then
  calling *classObject.method()*

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