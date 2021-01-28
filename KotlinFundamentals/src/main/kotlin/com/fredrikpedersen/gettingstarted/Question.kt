import java.lang.NumberFormatException

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

    fun biggestNumber(value1: Int, value2: Int): Int {
        return if (value1 > value2) {
            value1
        } else {
            value2
        }
    }
}