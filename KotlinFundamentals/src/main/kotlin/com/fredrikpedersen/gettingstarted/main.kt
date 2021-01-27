fun main(args: Array<String>) {

    val question: Question = Question()
    question.answer = "42"
    question.solution()
}

class Question {
    var answer: String? = null
    val correctAnswer = "42"
    val question: String = "What is the answer to life, the universe and everything?"

    fun solution() {

        if (answer != null) {
            val message =
                "Question: $question\nYou answered: $answer, which is ${if (answer == correctAnswer) "correct" else "wrong"}"
            println(message)
        }
        else {
            println("You haven't answered yet!")
        }
    }
}