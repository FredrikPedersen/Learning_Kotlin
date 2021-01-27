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