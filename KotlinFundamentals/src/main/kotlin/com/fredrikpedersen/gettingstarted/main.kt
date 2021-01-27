fun main(args: Array<String>) {

    val question = Question()
    question.answer = "42"
    question.display()
}

class Question {
    var answer: String = ""
    val correctAnswer = "42"
    val question: String = "What is the answer to life, the universe and everything?"

    fun display() {
        println("Question: $question\nYou answered: $answer, which is ${if (answer == correctAnswer) "correct" else "wrong"}")
    }
}