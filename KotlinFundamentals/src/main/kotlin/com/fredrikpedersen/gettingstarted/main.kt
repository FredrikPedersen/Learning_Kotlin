fun main(args: Array<String>) {

    val question = Question()
    question.answer = "42"
    print(question.solution())
}

class Question {
    var answer: String? = null
    val correctAnswer = "42"
    val question: String = "What is the answer to life, the universe and everything?"

    fun solution(): String {
        val message = "Question: $question\nYou answered: $answer, which is "

        return when(answer) {
            correctAnswer -> message + "correct"
            null -> "You haven't answered yet!"
            else -> message + "wrong"
        }
    }
}