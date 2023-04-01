package game

trait QuestionRepository {
    def nextQuestion(): Question
}

object QuestionRepository {
    def apply() = ???
}
