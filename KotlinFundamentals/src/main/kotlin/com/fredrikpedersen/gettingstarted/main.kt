fun main(args: Array<String>) {

    val question = Question()
    question.answer = "42"
    question.display()
    println("The answer is ${question.answer}")
}

class Question {
    var answer: String = ""
    val question: String = "What is the answer to life, the universe and everything?"

    fun display() {
        println("Q: $question + \nA: $answer")
    }
}