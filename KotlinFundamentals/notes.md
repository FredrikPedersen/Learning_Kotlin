# Notes

### Part 2 - Introduction

 - var is for mutable variables.
 - val is for immutable values.
 - public access modifier is default behaviour in Kotlin when nothing else is specified.
 - Unit is Kotlin for Void
 
 ### Part 3 - Getting Started With Kotlin
 
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