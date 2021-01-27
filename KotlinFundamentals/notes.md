# Notes

### General

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

### Looping Constructs

 - Kotlin's for loops are range based (like in Python)
 - Ranges can be over any datatype that implements the comparable interface, e.g:
	- 1..10
	- 'a'..'z'

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