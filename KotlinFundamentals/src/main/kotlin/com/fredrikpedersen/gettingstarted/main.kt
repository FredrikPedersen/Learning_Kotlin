import java.lang.NumberFormatException

fun main(args: Array<String>) {

    val question = Question()
    question.answer = question.convertAnswerToInt("42")
    print(question.solution())
}

class Question {
    var answer: Int? = null
    val correctAnswer = 42
    val question: String = "What is the answer to life, the universe and everything?"

    fun solution(): String {
        val message = "Question: $question\nYou answered: $answer, which is "

        return when(answer) {
            correctAnswer -> message + "correct"
            null -> "You haven't answered yet!"
            -1 -> "Your answer must be an Integer"
            else -> message + "wrong"
        }
    }

     fun convertAnswerToInt(userAnswer: String): Int {
        return try {
            Integer.parseInt(userAnswer)
        } catch (exception: NumberFormatException) {
            -1
        }
    }
}