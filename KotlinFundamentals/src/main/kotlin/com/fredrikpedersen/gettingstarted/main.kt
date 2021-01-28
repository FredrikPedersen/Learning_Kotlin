fun main(args: Array<String>) {
    val question = Question()
    question.answer = 42
    println(question.solution())

    question.answer = 69
    println(question.solution())
}

