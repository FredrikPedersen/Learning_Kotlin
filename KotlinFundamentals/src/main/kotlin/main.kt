import com.fredrikpedersen.Person

fun main(args: Array<String>) {
    val fredrik = Person("Fredrik")

    println(fredrik.display())
    fredrik.displayWithLambda(::printName)
}

fun printName (name: String) {
    println("Name: $name");
}